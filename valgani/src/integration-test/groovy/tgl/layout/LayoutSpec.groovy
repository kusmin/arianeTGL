package br.com.itexto.igra.layout

import grails.testing.mixin.integration.Integration
import grails.transaction.*
import spock.lang.Specification

@Integration
@Rollback
class LayoutSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "the layout configuration file should exist"() {
        expect:
            getClass().getClassLoader().getResourceAsStream("sideMenu.js") != null
    }

    void "at least one menu item should exist"() {
      expect:
        MenuItem.count() > 0
    }

    void "at least one sub menu item should exist"() {
      expect:
        MenuItem.executeQuery("from MenuItem mi where mi.parent is not null").size() > 0
    }

}
