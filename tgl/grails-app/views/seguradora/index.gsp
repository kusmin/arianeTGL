<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="tglMain" />
        <g:set var="entityName" value="${message(code: 'seguradora.label', default: 'Seguradora')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav" role="navigation">
            <ul>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-seguradora" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${seguradoraList}" />

            <div class="pagination">
                <g:paginate total="${seguradoraCount ?: 0}" />
            </div>
        </div>
    </body>
</html>