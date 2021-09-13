package tgl

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class SeguradoraController {

    SeguradoraService seguradoraService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond seguradoraService.list(params), model:[seguradoraCount: seguradoraService.count()]
    }

    def show(Long id) {
        respond seguradoraService.get(id)
    }

    def create() {
        respond new Seguradora(params)
    }

    def save(Seguradora seguradora) {
        if (seguradora == null) {
            notFound()
            return
        }

        try {
            seguradoraService.save(seguradora)
        } catch (ValidationException e) {
            respond seguradora.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'seguradora.label', default: 'Seguradora'), seguradora.id])
                redirect seguradora
            }
            '*' { respond seguradora, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond seguradoraService.get(id)
    }

    def update(Seguradora seguradora) {
        if (seguradora == null) {
            notFound()
            return
        }

        try {
            seguradoraService.save(seguradora)
        } catch (ValidationException e) {
            respond seguradora.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'seguradora.label', default: 'Seguradora'), seguradora.id])
                redirect seguradora
            }
            '*'{ respond seguradora, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        seguradoraService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'seguradora.label', default: 'Seguradora'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'seguradora.label', default: 'Seguradora'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
