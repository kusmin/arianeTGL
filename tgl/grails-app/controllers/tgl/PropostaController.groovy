package tgl

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PropostaController {

    PropostaService propostaService
    BuscaService buscaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond propostaService.list(params), model:[propostaCount: propostaService.count()]
    }

    def searchcount(String consultor, String dataInicial, String dataFinal){
        def buscar =  buscaService.buscarValoresQuantidadePropostas(consultor, dataInicial, dataFinal)
        render view: "searchcount", model: [buscar: buscar]
    }

    def searchsum(String consultor, String dataInicial, String dataFinal){
        def buscar =  buscaService.buscarValoresDosProdutos(consultor, dataInicial, dataFinal)
        render view: "searchsum", model: [buscar: buscar]
    }

    def show(Long id) {
        respond propostaService.get(id)
    }

    def create() {
        respond new Proposta(params)
    }

    def save(Proposta proposta) {
        if (proposta == null) {
            notFound()
            return
        }

        try {
            propostaService.save(proposta)
        } catch (ValidationException e) {
            respond proposta.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'proposta.label', default: 'Proposta'), proposta.id])
                redirect proposta
            }
            '*' { respond proposta, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond propostaService.get(id)
    }

    def update(Proposta proposta) {
        if (proposta == null) {
            notFound()
            return
        }

        try {
            propostaService.save(proposta)
        } catch (ValidationException e) {
            respond proposta.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'proposta.label', default: 'Proposta'), proposta.id])
                redirect proposta
            }
            '*'{ respond proposta, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        propostaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'proposta.label', default: 'Proposta'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'proposta.label', default: 'Proposta'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
