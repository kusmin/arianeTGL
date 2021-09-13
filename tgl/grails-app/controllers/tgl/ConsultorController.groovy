package tgl

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ConsultorController {

    ConsultorService consultorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond consultorService.list(params), model:[consultorCount: consultorService.count()]
    }

    def show(Long id) {
        respond consultorService.get(id)
    }

    def create() {
        respond new Consultor(params)
    }

    def save(Consultor consultor) {
        if (consultor == null) {
            notFound()
            return
        }

        try {
            consultorService.save(consultor)
        } catch (ValidationException e) {
            respond consultor.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'consultor.label', default: 'Consultor'), consultor.id])
                redirect consultor
            }
            '*' { respond consultor, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond consultorService.get(id)
    }

    def update(Consultor consultor) {
        if (consultor == null) {
            notFound()
            return
        }

        try {
            consultorService.save(consultor)
        } catch (ValidationException e) {
            respond consultor.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'consultor.label', default: 'Consultor'), consultor.id])
                redirect consultor
            }
            '*'{ respond consultor, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        consultorService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'consultor.label', default: 'Consultor'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'consultor.label', default: 'Consultor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
