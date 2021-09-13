package tgl.cache

import grails.gorm.transactions.Transactional


@Transactional
class TglCacheService implements tglCache {

    def configurationService

    tglCache cacheManager

    void init() {
      String cacheType = configurationService.info('tgl.cache.mode')
      if (! cacheType) {
        cacheType = 'memory'
      }
      switch (cacheType) {
        case 'redis':
          this.cacheManager = new tglRedisCache()
          this.cacheManager.init(configurationService)
          break
        case 'memory':
        default:
          this.cacheManager = new tglMemoryCache()
      }
    }


    String get(String code) {
      cacheManager?.get(code)
    }


    void put(String code, String value) {
      cacheManager?.put(code, value)
    }


    void remove(String code) {
      cacheManager?.remove(code)
    }


    void clean() {
      cacheManager?.clean()
    }


}
