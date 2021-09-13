package tgl
import tgl.layout.*
class TglLayoutTagLib {
    static defaultEncodeAs = [taglib:'raw']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = "tglLayout"

    def securityService
    def layoutService
    def tglCacheService

    def input = {attr, body -> 
      out << g.render(template:'/comps/input', model:[type:attr.type, value:attr.value, label:attr.label, name:attr.name])
    }

    def infoRegistro = {attr, body ->
      out << g.render(template:'/comps/detalheRegistro', model:[objeto:attr.objeto])
    }

    
    def display = {attr, body -> 
      if (attr.value == null) {
        out << ""
        return
      }
      out << g.render(template:'/comps/display', model:[label:attr.label, value:attr.value])
    }

    /**
      Usado para renderizar o menu lateral
    */
    def sideMenu = {attr, body ->
      def user = securityService.currentUser()
      def key = "tgl.layout.sidemenu.user.${user.username}"

      def cachedContent = tglCacheService.get(key)
      if (cachedContent) {
        out << cachedContent
        return
      }

      def menus = MenuItem.executeQuery("from MenuItem mi where mi.parent is null order by mi.order")
      cachedContent = render(template:'/layouts/tgl/sideMenu', model:[menus:menus])
      tglCacheService.put(key.toString(), cachedContent.toString())

      out << cachedContent
    }

    def username = {àttr, body ->
      out << securityService.currentUser()?.username
    }

}
