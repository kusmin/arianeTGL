package tgl.security

class Perfil {

    String nome
    Boolean ativo = true
    String descricao

    static hasMany = [permissoes:Role]

    static constraints = {
        nome nullable:false, blank:false, maxSize:64, unique:true
        ativo nullable:false
        descricao nullable:true, blank:true, maxSize:128
    }
}
