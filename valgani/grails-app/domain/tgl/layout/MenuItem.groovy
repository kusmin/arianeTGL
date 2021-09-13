package tgl.layout

import tgl.layout.LayoutService

/**
  Representa um item de menu do tgl
*/
class MenuItem {

    def layoutService

    String title /* Título do menu */
    String code /* Código do menu, para internacionalização (opcional) */
    String icon /* Código do font-awesome para a renderização do ícone do menu. Se não estiver presente, nada será renderizado */
    Integer order /* Ordem de exposição do menu */
    String roles /* Lista de permissões que possibilitam a renderização do menu, separadas por vírgula. Se for nulo, o menu sempre será renderizado */
    String controller /* Se presente, indica para qual controlador o item de menu realizará o redirecionamento */
    String action /* Se presente, indica para qual action o item de menu realizará o redirecionamento */
    String uri /* Se presente iremos renderizar um link do tipo URI */

    def beforeValidate() {
      if (order == null) {
        if (parent) {
          order = MenuItem.countByParent(parent) + 1
        } else {
          order = MenuItem.countByParentIsNull() + 1
        }
      }
    }

    boolean podeRenderizar() {
      layoutService.shouldRender(this)
    }

    static belongsTo = [
      parent: MenuItem
    ]

    static constraints = {
      title nullable:false, blank:false, maxSize:128
      code nullable:true, blank:true, maxSize:128
      icon nullable:true, blank:true, maxSize:64
      order nullable:false, blank:false
      roles nullable:true, blank:true, maxSize:255
      parent nullable:true
      controller nullable:true, blank:true, maxSize:64
      action nullable:true, blank:true, maxSize:64
      uri nullable:true, blank:true, maxSize:255
    }

    static mapping = {
      table "menu_item"
      order column:'_order'
    }
}
