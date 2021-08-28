package tgl

import grails.gorm.services.Service

@Service(Seguradora)
interface SeguradoraService {

    Seguradora get(Serializable id)

    List<Seguradora> list(Map args)

    Long count()

    void delete(Serializable id)

    Seguradora save(Seguradora seguradora)

}