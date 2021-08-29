package tgl

class Proposta {

    Integer numeroProposta
    BigDecimal previdencia
    BigDecimal aporte
    BigDecimal risco
    BigDecimal consorcio
    Date dataAtual = new Date()
    String status



    static belongsTo = [
        cliente:Cliente,
        consultor:Consultor,
        gerente:Gerente,
        seguradora:Seguradora
        ]

    static constraints = {
        numeroProposta nullable:true, blank: true
        previdencia nullable:true
        aporte nullable:true
        risco nullable:true
        consorcio nullable:true
        cliente nullable:false
        consultor nullable:false
        gerente nullable:false
        seguradora nullable:false
        status nullable:false, blank: false
    }

    static mapping = {
        consultor lazy: false
    }
}
