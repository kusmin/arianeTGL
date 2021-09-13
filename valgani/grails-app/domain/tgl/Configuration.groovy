package tgl

class Configuration {

    String code
    String value

    static constraints = {
      code nullable:false, blank:false, unique:true, maxSize:128
      value nullable:false, blank:false, maxSize:1024
    }

    static mapping = {
      version false
    }
}
