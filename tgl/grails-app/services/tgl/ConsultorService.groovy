package tgl

import grails.gorm.services.Service

@Service(Consultor)
interface ConsultorService {

    Consultor get(Serializable id)

    List<Consultor> list(Map args)

    Long count()

    void delete(Serializable id)

    Consultor save(Consultor consultor)

}