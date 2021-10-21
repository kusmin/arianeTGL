package tgl

class Consultor {

    String nome
    String description

//teste
    String toString(){
        this.nome
    }
    static constraints = {
        nome unique:true, nullable:false, blank: false
    }
}
