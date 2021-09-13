package tgl.email

import grails.gorm.transactions.Transactional
import org.apache.commons.mail.*

@Transactional
class MailerService {

    private String host
    private Integer port
    private String username
    private String password
    private String from

    def configurationService

    void init() {
      host = configurationService.info('tgl.smtp.host')
      port = configurationService.info('tgl.smtp.port') as Integer
      username = configurationService.info('tgl.smtp.username')
      password = configurationService.info('tgl.smtp.password')
      
      from = configurationService.info('tgl.smtp.from')
    }

    /**
      Realiza o envio de um e-mail usando procedimentos de baixo nível
    */
    def send(String subject, String from, String to, String body) {
      HtmlEmail email = new HtmlEmail()
      email.setHostName(host)
      email.addTo(to)
      email.setFrom(from)
      email.setSmtpPort(port)
      email.setAuthentication(username, password)

      email.setSubject(subject)
      email.setHtmlMsg(body)

      def message = new EmailMessage(from:from, to:to, message:body, subject:subject)

      try {
        email.send()
        message.dateSent = new Date()
      } catch (Throwable t) {
        message.retries = 1
      }
      return message.save(failOnError:true)
    }
}
