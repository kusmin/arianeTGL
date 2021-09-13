package tgl.security

import grails.gorm.transactions.Transactional

import tgl.security.User
import tgl.security.Perfil
import tgl.security.UserRole
import tgl.security.Role

@Transactional
class SecurityService {
    def springSecurityService

    void init(){
        if( !Role.findByAuthority('ROLE_USER')){
            // ROLE_USER é o último role incluído. Se o registro existe, 
            // este script já foi executado

            def rolesInfo = getClass().getClassLoader().getResourceAsStream("roles.info")
            if (rolesInfo){
                def linhas = rolesInfo.readLines()
                for (String linha in linhas){
                    def componente = linha.split(';')
                    if(!Role.findByAuthority(componente[0])){
                        Role role = new Role(authority:componente[0], description:componente[1])
                        role.save(failOnError:true)
                    }
                }
            }

            Perfil perfilAdmin = new Perfil(nome:"admintgl", ativo:true, descricao:"Perfil ADMIN")
              def permissoes = Role.list()
              for (perm in permissoes) {
                 perfilAdmin.addToPermissoes(perm)
              }
             perfilAdmin.save(failOnError:true, flush:true)

               User admin = User.findByUsername("admintgl")
                if (! admin) {
                  admin = new User(username:"admintgl", password:"admin1234", perfil: perfilAdmin).save(failOnError:true)
                }
        }
    }
     User currentUser() {
      springSecurityService.getCurrentUser()
    }
}

