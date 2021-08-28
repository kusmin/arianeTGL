package tgl

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PropostaServiceSpec extends Specification {

    PropostaService propostaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Proposta(...).save(flush: true, failOnError: true)
        //new Proposta(...).save(flush: true, failOnError: true)
        //Proposta proposta = new Proposta(...).save(flush: true, failOnError: true)
        //new Proposta(...).save(flush: true, failOnError: true)
        //new Proposta(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //proposta.id
    }

    void "test get"() {
        setupData()

        expect:
        propostaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Proposta> propostaList = propostaService.list(max: 2, offset: 2)

        then:
        propostaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        propostaService.count() == 5
    }

    void "test delete"() {
        Long propostaId = setupData()

        expect:
        propostaService.count() == 5

        when:
        propostaService.delete(propostaId)
        sessionFactory.currentSession.flush()

        then:
        propostaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Proposta proposta = new Proposta()
        propostaService.save(proposta)

        then:
        proposta.id != null
    }
}
