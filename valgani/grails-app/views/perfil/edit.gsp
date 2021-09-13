<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="tglMain" />
        <g:set var="entityName" value="${message(code: 'perfil.label', default: 'Perfil')}" />
        <title>${perfil.id ? 'Editar ' : 'Criar '} Perfil</title>
    </head>
    <body>
        
        
            <ul class="nav nav-comandos">
                
                <li class="nav-item"><g:link class="nav-link" action="index">Perfis</g:link></li>
                
            </ul>
        
        <div id="edit-perfil" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.perfil}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.perfil}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="persistir">
                <g:if test="${perfil.id}">
                    <input type="hidden" name="id" value="${perfil.id}"/>
                </g:if>
                <g:hiddenField name="version" value="${this.perfil?.version}" />
                <fieldset class="form">
                    <div class="form-group">
                        <label for="nome">Nome</label>
                        <input type="text" name="nome" class="form-control underlined" value="${perfil.nome}"/>
                    </div>
                    <div class="form-group">
                        <label for="nome">Descrição</label>
                        <input type="text" name="descricao" class="form-control underlined" value="${perfil.descricao}"/>
                    </div>
                    <div class="form-group">
                        <label for="nome">Ativo</label>
                        <g:checkBox name="ativo" value="${perfil.ativo}"/>
                    </div>
                    <h3>Permissões</h3>
                    <div>
                        <g:each in="${roles}" var="role">
                            <label>
                                <g:checkBox name="roles" value="${role.authority}" checked="${perfil.permissoes?.contains(role)}"/>
                            ${role.description}
                            </label>

                            <br/>
                        </g:each>
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
