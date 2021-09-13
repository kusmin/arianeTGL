package tgl

import org.hibernate.envers.Audited;
import java.util.regex.Pattern

@Audited
class Arquivo {

    String chave
    String nomeOriginal
    String bucket
    String tipo = 'filesystem'
    Date dataInclusao

    def beforeValidate() {
        if(this.nomeOriginal && this.nomeOriginal.length() > 64){
            String[] s = this.nomeOriginal.split(Pattern.quote("."))
            String extensao = s[s.size() - 1]
            this.nomeOriginal = this.nomeOriginal.substring(0, (64 - (extensao.length() + 1))) + "." + extensao
        }
    }

    def beforeInsert() {
        if (this.dataInclusao == null) {
            this.dataInclusao = new Date()
        }
    }

    static constraints = {
        chave nullable:false, blank:false, maxSize:64, unique:true
        nomeOriginal nullable:false, blank:false, maxSize:64
        bucket nullable:false, blank:false, maxSize:128
        tipo nullable:false, blank:false, maxSize:32
        dataInclusao nullable:false
    }

    static mapping = {
        table 'tgl_arquivo'
        version false
        nomeOriginal column: 'nome_original'
        dataInclusao column: 'data_inclusao'
    }
}
