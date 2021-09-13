package tgl.cache

import grails.testing.mixin.integration.Integration
import grails.transaction.*
import spock.lang.Specification

@Integration
@Rollback
class CacheManagerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def tglCacheService

    void "tgl chache iniciado"() {
        expect:
            tglCacheService.cacheManager != null
    }

    void "testar valor de caches"() {
      String randomKey = UUID.randomUUID().toString()
      String randomValue = UUID.randomUUID().toString()
      tglCacheService.put(randomKey, randomValue)
      String cachedValue = tglCacheService.get(randomKey)
      tglCacheService.remove(randomKey)
      String afterRemoval = tglCacheService.get(randomKey)
      expect:
        cachedValue == randomValue
        afterRemoval == null
    }
}
