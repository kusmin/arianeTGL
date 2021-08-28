package tgl

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ConsultorServiceSpec extends Specification {

    ConsultorService consultorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Consultor(...).save(flush: true, failOnError: true)
        //new Consultor(...).save(flush: true, failOnError: true)
        //Consultor consultor = new Consultor(...).save(flush: true, failOnError: true)
        //new Consultor(...).save(flush: true, failOnError: true)
        //new Consultor(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //consultor.id
    }

    void "test get"() {
        setupData()

        expect:
        consultorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Consultor> consultorList = consultorService.list(max: 2, offset: 2)

        then:
        consultorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        consultorService.count() == 5
    }

    void "test delete"() {
        Long consultorId = setupData()

        expect:
        consultorService.count() == 5

        when:
        consultorService.delete(consultorId)
        sessionFactory.currentSession.flush()

        then:
        consultorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Consultor consultor = new Consultor()
        consultorService.save(consultor)

        then:
        consultor.id != null
    }
}
