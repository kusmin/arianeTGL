package tgl

import grails.gorm.transactions.Transactional
import java.text.SimpleDateFormat

@Transactional
class BuscaService {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd")
    def buscarValoresQuantidadePropostas(String nomeConsultor, String dataInicial, String dataFinal ){
        Date dtFim = null
        Date dtInicio = null
        if(dataFinal){
            dtFim = dateFormat.parse(dataFinal)
        }
        if(dataInicial){
            dtInicio = dateFormat.parse(dataInicial)
        }
          def resultado = Proposta.withCriteria{
            if (dtInicio && dtFim) {
                between "dataAtual", dtInicio, dtFim
            }else if(dtInicio){
                ge "dataAtual", dtInicio
            }else if(dtFim){
                le "dataAtual", dtFim
            }
            if(nomeConsultor)
                consultor{
                  eq "nome", "${nomeConsultor}"
              }
            if(!dtInicio && !dtFim && !nomeConsultor){
                  Proposta.findAll()
              }
            projections{
                groupProperty "consultor"
                property "consultor"
                count "consultor"
                count "aporte"
                count "previdencia"
                count "risco"
                count "consorcio"  
              }
            
          }
        resultado
    }

    def buscarValoresDosProdutos(String nomeConsultor, String dataInicial, String dataFinal ){
        Date dtFim = null
        Date dtInicio = null
        if(dataFinal){
            dtFim = dateFormat.parse(dataFinal)
        }
        if(dataInicial){
            dtInicio = dateFormat.parse(dataInicial)
        }
          def resultado = Proposta.withCriteria{
            if (dtInicio && dtFim) {
                between "dataAtual", dtInicio, dtFim
            }else if(dtInicio){
                ge "dataAtual", dtInicio
            }else if(dtFim){
                le "dataAtual", dtFim
            }
              if(nomeConsultor)
                consultor{
                  eq "nome", "${nomeConsultor}"
              }
            if(!dtInicio && !dtFim && !nomeConsultor){
                  Proposta.findAll()
              }
            projections{
                groupProperty "consultor"
                property "consultor"
                sum "aporte"
                sum "previdencia"
                sum "risco"
                sum "consorcio"
              }
            
          }
        resultado
    }
    
}
