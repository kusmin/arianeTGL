package tgl.security

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

import grails.gorm.transactions.Transactional

@Transactional
@Secured(["ROLE_ADMIN"])
class UserController {

    def securityService

    def index() {
      [perfis:Perfil.list(sort:'nome')]
    }

    def search() {
      
      def users = User.withCriteria {
        if (params.username) {
          ilike 'username', "%${params.username}%"
        }
        if (params.nomeCompleto) {
          ilike 'nomeCompleto', "%${params.nomeCompleto}%"
        }
        if (params.enabled) {
          if(params.enabled == "Ativos"){
            eq 'enabled', true
          }else{
            eq 'enabled', false
          }
        }
        if (params.perfil) {
          perfil {
            eq 'id', params.long('perfil')
          }
        }
        order 'username', 'asc'
      }

      render(view:'index', model:[users:users, perfis:Perfil.findAllByAtivo(true, [sort:'nome'])])
    }

    def create() {
      render(view:'show', model:[user:new User(), perfis:Perfil.findAllByAtivo(true, [sort:'nome'])])
    }

    @Secured(["ROLE_ADMIN","ROLE_USER"])
    def show(User user) {
      if (! user) {
        response.status = 404
        return
      }
      
      def roles = []
      roles.addAll(user.perfil.permissoes)
      roles = roles.sort { it.description }
      

      [user:user, roles:roles, perfis:Perfil.list([sort:'nome'])]

    }

    @Secured(["ROLE_ADMIN","ROLE_USER"])
    def update(User user) {
      if (! user) {
        response.status = 404
        return
      }

      if (! securityService.currentUser().isAdmin() && user.id != securityService.currentUser().id) {
        response.status = 403
        render "Acesso negado"
        return
      }

      if (! user.validate()) {
        flash.message = "Dados inválidos."
        render(view:'show', model:[user:user, roles:Role.list(sort:'authority')])
        return
      }

      user.save(failOnError:true)

      redirect(action:'show', id:user.id)
    }

    def save(User user) {
      if (! user.validate()) {
        flash.message = "Não foi possível criar seu usuário. Dados inválidos. ${user.errors}"
        render(view:'show', model:[user:user, roles:Role.list(sort:'authority')])
        return
      }
      user = user.save(failOnError:true, flush:true)
      for (id in params.list('role')) {
        new UserRole(user:user, role:Role.get(id as Long)).save(failOnError:true, flush:true)
      }

      redirect(action:'show', id:user.id)
    }

    @Secured(["ROLE_ADMIN","ROLE_USER"])
    def updatePassword(User user) {
      if (! user) {
        response.status = 404
        return
      }
      if (!user.password) {
        response.status = 400
        flash.error = "Senha inválida"
        redirect(action:'show', id:user.id)
        return
      }
      if (! securityService.currentUser().isAdmin() && user.id != securityService.currentUser().id) {
        response.status = 403
        render "Acesso negado"
        return
      }
      user.password = params.password
      user.save(failOnError:true, flush:true)
      flash.message = "Senha alterada com sucesso"
      redirect(action:'show', id:user.id)
    }
}
