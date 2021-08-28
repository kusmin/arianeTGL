package tgl

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SeguradoraServiceSpec extends Specification {

    SeguradoraService seguradoraService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Seguradora(...).save(flush: true, failOnError: true)
        //new Seguradora(...).save(flush: true, failOnError: true)
        //Seguradora seguradora = new Seguradora(...).save(flush: true, failOnError: true)
        //new Seguradora(...).save(flush: true, failOnError: true)
        //new Seguradora(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //seguradora.id
    }

    void "test get"() {
        setupData()

        expect:
        seguradoraService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Seguradora> seguradoraList = seguradoraService.list(max: 2, offset: 2)

        then:
        seguradoraList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        seguradoraService.count() == 5
    }

    void "test delete"() {
        Long seguradoraId = setupData()

        expect:
        seguradoraService.count() == 5

        when:
        seguradoraService.delete(seguradoraId)
        sessionFactory.currentSession.flush()

        then:
        seguradoraService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Seguradora seguradora = new Seguradora()
        seguradoraService.save(seguradora)

        then:
        seguradora.id != null
    }
}
