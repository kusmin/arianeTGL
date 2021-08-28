package tgl

import grails.gorm.services.Service

@Service(Proposta)
interface PropostaService {

    Proposta get(Serializable id)

    List<Proposta> list(Map args)

    Long count()

    void delete(Serializable id)

    Proposta save(Proposta proposta)

}