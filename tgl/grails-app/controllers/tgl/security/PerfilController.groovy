package tgl.security

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured


@Secured(["ROLE_ADMIN"])
class PerfilController {

    PerfilService perfilService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond perfilService.list(params), model:[perfilCount: perfilService.count()]
    }

    def show(Perfil perfil) {
        def roles = Role.findAll()
        render(view:'edit', model:[perfil:perfil, roles: roles])
    }

    def persistir() {
        def perfil = null
        if (params.id) {
            perfil = Perfil.get(params.long('id'))
            if (! perfil) {
                response.status = 404
                render "Não encontrado"
            }
        } else {
            perfil = new Perfil(params)
            perfil.permissoes = []
        }
        perfil.nome = params.nome
        perfil.descricao = params.descricao
        perfil.ativo = (params.ativo == 'on')
        perfil.permissoes = []
        for (permissao in params.list('roles')) {
            perfil.addToPermissoes(Role.findByAuthority(permissao))
        }
        perfil = perfil.save(failOnError:true, flush:true)
        redirect(action:'show', id:perfil.id)
    }

    def create() {
        def roles = Role.findAll()
        render(view: 'edit', model:[perfil:new Perfil(), roles: roles])
    }

    def save(Perfil perfil) {
        if (perfil == null) {
            notFound()
            return
        }

        try {
            perfilService.save(perfil)
        } catch (ValidationException e) {
            respond perfil.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'perfil.label', default: 'Perfil'), perfil.id])
                redirect perfil
            }
            '*' { respond perfil, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond perfilService.get(id)
    }

    def update(Perfil perfil) {
        if (perfil == null) {
            notFound()
            return
        }

        try {
            perfilService.save(perfil)
        } catch (ValidationException e) {
            respond perfil.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'perfil.label', default: 'Perfil'), perfil.id])
                redirect perfil
            }
            '*'{ respond perfil, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        perfilService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'perfil.label', default: 'Perfil'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'perfil.label', default: 'Perfil'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
