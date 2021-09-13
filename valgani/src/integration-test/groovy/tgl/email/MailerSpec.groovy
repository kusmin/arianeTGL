package br.com.itexto.igra.email

import grails.testing.mixin.integration.Integration
import grails.transaction.*
import spock.lang.Specification

@Integration
@Rollback
class MailerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def mailerService

    void "enviando um simples e-mail"() {
        def message = mailerService.send("Teste do tgl", "renan.lagee@gmail.com.br", "renan.lagee@gmail.com.br", "<html>Um teste vindo do IGra</html>")
        expect:
            message != null
    }
}
