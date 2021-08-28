package tgl

class Seguradora {

    String nome

    String toString(){
        this.nome
    }
    static constraints = {
        nome unique:true, nullable:false, blank: false
    }
}
