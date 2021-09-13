<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="tglMain" />
        <g:set var="entityName" value="${message(code: 'proposta.label', default: 'Proposta')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav" role="navigation">
            <ul>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <li><g:link class="list" action="searchcount">Buscar Contagem de Propostas</g:link></li>
                <li><g:link class="list" action="searchsum">Buscar Valor Total das Propostas</g:link></li>
            </ul>
        </div>
        <div id="list-proposta" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${propostaList}" />


            <div class="pagination">
                <g:paginate total="${propostaCount ?: 0}" />
            </div>
        </div>
    </body>
</html>