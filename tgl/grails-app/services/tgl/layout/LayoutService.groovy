package tgl.layout

import grails.gorm.transactions.Transactional

import groovy.json.*

/**
  Usado na renderização de layouts do projeto tgl, tal como definição de menus, etc.
*/
@Transactional
class LayoutService {

    def securityService

    /**
      Retorna true caso o menu passado como parâmetro possa ser renderizado
      pelo usuário
    */
    boolean shouldRender(MenuItem menu) {
      println "Verificando o menu ${menu.title}"
      def user = securityService.currentUser()
      if (! menu.roles || menu.roles == "IS_AUTHENTICATED_FULLY") {
        return true // Acesso publico
      } else {
        return user.getAuthorities().find({auth -> menu.roles.contains(auth.authority)}) != null
      }
    }

    /**
      Cria todos os itens de menu durante a inicialização do sistema
    */
    void init() {
      def menuConfiguration = getClass().getClassLoader().getResourceAsStream("sideMenu.js")
      if (menuConfiguration) {
        def jsonSlurper = new JsonSlurper()
        def content = jsonSlurper.parse(menuConfiguration)
        for (item in content) {
          println "Verificando o item ${item}"
          /* Parent menu itens */
          def currentMenu = MenuItem.findByCode(item.code)
          if (! currentMenu) {
            currentMenu = new MenuItem(title:item.title, code:item.code, icon:item.icon, roles:item.roles, controller:item.controller, action:item.action, uri:item.uri).save(failOnError:true)
          } else {
            currentMenu.title = item.title
            currentMenu.code = item.code
            currentMenu.icon = item.icon
            currentMenu.roles = item.roles
            currentMenu.controller = item.controller
            currentMenu.action = item.action
            currentMenu.uri = item.uri
            currentMenu.save(failOnError:true)
          }
          if (item.itens) {
              createSubMenus(currentMenu, item.itens)
          }
        }
      }
    }

    private void createSubMenus(MenuItem parent, itens) {
      for (item in itens) {
        def currentMenu = MenuItem.findByCode(item.code)
        if (! currentMenu) {
          currentMenu = new MenuItem(title:item.title, code:item.code, icon:item.icon, roles:item.roles, parent:parent, controller:item.controller, action:item.action, uri:item.uri).save(failOnError:true)
        } else {
          currentMenu.title = item.title
          currentMenu.code = item.code
          currentMenu.icon = item.icon
          currentMenu.roles = item.roles
          currentMenu.parent = parent
          currentMenu.controller = item.controller
          currentMenu.action = item.action
          currentMenu.uri = item.uri
          currentMenu.save(failOnError:true)
        }
        if (item.itens) {
            createSubMenus(currentMenu, item.itens)
        }
      }
    }

}
