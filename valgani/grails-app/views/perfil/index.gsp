<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="tglMain" />
        <g:set var="entityName" value="${message(code: 'perfil.label', default: 'Perfil')}" />
        <title>Perfis</title>
    </head>
    <body>
        
        
        
        <ul class="nav nav-comandos">
            
            <li class="nav-item">
                <g:link class="nav-link" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
            </li>
        </ul>
        
        <div id="list-perfil">
            
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${perfilList}" properties="['nome', 'descricao', 'ativo']"/>

            <div class="pagination">
                <g:paginate total="${perfilCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
