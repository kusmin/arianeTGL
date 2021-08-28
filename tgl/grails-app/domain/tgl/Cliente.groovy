package tgl

class Cliente {

    String nome
    Integer cpf

    String toString(){
        this.nome
    }
    static constraints = {
        nome unique:true, nullable:false, blank: false
        cpf unique:true, nullable:false, blank: false
    }
}
