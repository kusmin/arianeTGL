package tgl

class Gerente {

    String nome

    String toString(){
        this.nome
    }
    static constraints = {
        nome unique:true, nullable:false, blank: false
    }
}
