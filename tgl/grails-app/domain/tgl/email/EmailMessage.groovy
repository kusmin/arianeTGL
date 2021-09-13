package tgl.email

class EmailMessage {

    String from
    String to
    String subject
    String message

    Date dateCreated
    Date dateSent
    Integer retries = 0

    def beforeValidate() {
      if (this.dateCreated == null) {
        this.dateCreated = new Date()
      }
    }


    static constraints = {
      from nullable:false, blank:false, maxSize:128
      to nullable:false, blank:false, maxSize:128
      subject nullable:false, blank:false, maxSize:128
      message nullable:false, blank:false, maxSize:16384
      dateCreated nullable:false
      dateSent nullable:true
      retries nullable:false
    }

    static mapping = {
      table 'email_message'
      from column:'_from'
    }

}
