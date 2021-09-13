package tgl

import grails.plugin.springsecurity.annotation.Secured
import grails.converters.*

@Secured(["ROLE_ADMIN"])
class ConfigurationController {

    def configurationService

    def index() {

    }

    def search(String code) {
      def configurations = Configuration.findAllByCodeLike("%${params.code}%", [sort:'code'])
      render configurations as JSON
    }

    def update(COConfiguration config) {
      configurationService.update(config.code, config.value)
      response.status = 200
    }
}

class COConfiguration {
  String code
  String value
  String toString() {
    "${this.code} - ${this.value}"
  }
}
