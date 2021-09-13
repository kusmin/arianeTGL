package tgl.cache

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class CacheController {

    def tglCacheService

    /**
      Realiza a limpeza do cache
    */
    def clean() {
      tglCacheService.clean()
      render "Ok"
    }
}
