<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'proposta.label', default: 'Proposta')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-proposta" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-proposta" class="content scaffold-list" role="main">
            <h1>Buscar Valores de Propostas</h1>
            <div class="form-inline mt-1 mg-3">
            <g:form class="form-row" action= "searchsum" method="GET">
                <div class="col-auto my-1">
                    <p>Data Inicial: </p><input type="date" class="form-control form-control" value = "${new Date ()}"  name="dataInicial" noSelection="['':'Selecione uma data inicial']"/>
                </div>
                <div class="col-auto my-1">
                    <p>Data Final: </p><input type="date" class="form-control form-control" value = "${new Date ()}"  name="dataFinal" noSelection="['':'Selecione uma data final']"/>
                </div>
                <div class="col-auto my-1">
                    <p>Consultor: </p><g:select class=" form-control custom-select mr-sm-2 " name="consultor" from="${tgl.Consultor?.list()}" value="${Consultor?.nome}" optionValue="nome" optionKey = "nome" noSelection="['':'Selecione um consultor']" />
                </div>
                <div class="col-auto my-1">
                    <g:submitButton class="btn btn-secondary mt-4" value="Buscar" name="buscar" />
                </div>
                </g:form>
            </div>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:if test="${buscar}">
            <g:set var="aporte" value="${0.00}"/>
            <g:set var="previdencia" value="${0.00}"/>
            <g:set var="risco" value="${0.00}"/>
            <g:set var="consorcio" value="${0.00}"/>
            <table class="table">
                    <thead class="">
                        <tr>
                            <th>Consultor</th>
                            <th>Aporte</th>
                            <th>Previdência</th>
                            <th>Risco</th>
                            <th>Consórcio</th>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each var="dados" in="${buscar}" status="i">
                            <tr>
                                <td>${dados[0]}</td>
                                <td>${dados[2] == null ? "0.00" : dados[2]} <g:set var="aporte" value="${aporte + (dados[2] == null ? 0.00 : dados[2])}"/> </td>
                                <td>${dados[3] == null ? "0.00" : dados[3]} <g:set var="previdencia" value="${previdencia + (dados[3] == null ? 0.00 : dados[3])}"/></td>
                                <td>${dados[4] == null ? "0.00" : dados[4]} <g:set var="risco" value="${risco + (dados[4] == null ? 0.00 : dados[4])}"/></td>
                                <td>${dados[5] == null ? "0.00" : dados[5]} <g:set var="consorcio" value="${consorcio + (dados[5] == null ? 0.00 : dados[5])}"/></td>
                            </tr>
                        </g:each>
                    </tbody>
                    <tfoot>
                            <tr>
                                <td>Total</td>
                                <td>${aporte}</td>
                                <td>${previdencia}</td>
                                <td>${risco}</td>
                                <td>${consorcio}</td>
                            </tr>
                    </tfoot>
                </table>
            </g:if>
            <div class="pagination">
                <g:paginate total="${propostaCount ?: 0}" />
            </div>
        </div>
    </body>
</html>