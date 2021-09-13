package tgl.security

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_USER"])
class HomeController {

    def springSecurityService

    def index() {
        def usuario = springSecurityService.currentUser
        render(view: "index")
    }
}
