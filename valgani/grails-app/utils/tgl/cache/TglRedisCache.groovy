package tgl.cache

import tgl.ConfigurationService

import redis.clients.jedis.*
import java.time.Duration


class TglRedisCache implements tglCache {

  private ConfigurationService configurationService

  private JedisPool jedisPool

  private boolean encryptKey = false

  private Integer expires = 60 /* Tempo em segundos até a expiração da chave no cache */

  private Jedis getJedis() {
    jedisPool?.getResource()
  }



  void init(ConfigurationService configurationService) {
    this.configurationService = configurationService

    JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMaxTotal(configurationService.info('tgl.cache.redis.pool.maxTotal', '128') as Integer);
    poolConfig.setMaxIdle(configurationService.info('tgl.cache.redis.pool.maxIdle', '128') as Integer);
    poolConfig.setMinIdle(configurationService.info('tgl.cache.redis.pool.minIdle', '16') as Integer);
    poolConfig.setTestOnBorrow(configurationService.info('tgl.cache.redis.pool.testOnBorrow', 'true') == "true");
    poolConfig.setTestOnReturn(configurationService.info('tgl.cache.redis.pool.testOnReturn', 'true') == "true");
    poolConfig.setTestWhileIdle(configurationService.info('tgl.cache.redis.pool.testWhileIdle', 'true') == "true");
    poolConfig.setMinEvictableIdleTimeMillis(configurationService.info('tgl.cache.redis.pool.minEvictableIdleTimeMillis', '60000') as Long);
    poolConfig.setTimeBetweenEvictionRunsMillis(configurationService.info('tgl.cache.redis.pool.timeBetweenEvictionRunsMillis', '30000') as Long);
    poolConfig.setNumTestsPerEvictionRun(configurationService.info('tgl.cache.redis.pool.numTestsPerEvictionRun', '3') as Integer);
    poolConfig.setBlockWhenExhausted(configurationService.info('tgl.cache.redis.pool.blockWhenExhausted', 'true') == "true");

    this.encryptKey = configurationService.info('tgl.cache.redis.encryptKey', 'true') == "true"

    String host = configurationService.info('tgl.cache.redis.host')
    if (! host) {
      throw new RuntimeException("tglCacheException: no Redis Host!")
    }
    jedisPool = new JedisPool(poolConfig, host)

    this.expires = configurationService.info('tgl.cache.redis.expires', '60') as Integer

  }

  private String getKey(String code) {
    this.encryptKey ? code?.encodeAsSHA256() : code
  }

  /**
    Retorna uma string armazenada no cache
  */
  String get(String code) {
    Jedis jedis = getJedis()
    try {
      String value = jedis?.get(getKey(code))
      return "" == value ? null : value
    } finally {
      jedis?.close()
    }
  }

  /**
    Armazena uma string no cache
  */
  void put(String code, String value) {
    Jedis jedis = getJedis()
    try {
      if (value == null) {
        value = ""
      }
      jedis?.set(getKey(code), value)
      jedis?.expire(getKey(code), this.expires)
    } finally {
      jedis?.close()
    }

  }

  /**
    Remove um item do cache
  */
  void remove(String code) {
    Jedis jedis = getJedis()
    try {
      jedis?.del(getKey(code))
    } finally {
      jedis?.close()
    }
  }

  /**
    Limpa todas as chaves do cache
  */
  void clean() {
    Jedis jedis = getJedis()
    try {
      jedis?.flushAll()
    } finally {
      jedis?.close()
    }
  }
}
