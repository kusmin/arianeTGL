package tgl

import tgl.security.User
import tgl.security.Perfil
import tgl.security.UserRole
import tgl.security.Role

import grails.gorm.transactions.Transactional

class BootStrap {

    def configurationService
    def tglCacheService
    def securityService
    def layoutService
    def mailerService
    def storageService


    def init = {
        configurationService.init()
        tglCacheService.init()
        securityService.init()
        layoutService.init()
        mailerService.init()
        storageService.init()
    }
  
    def destroy = {
    }
}