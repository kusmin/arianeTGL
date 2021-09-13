<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="tglMain"/>
    <title>${user.id ? 'Editar usuário' : 'Criar usuário'}</title>
  </head>
  <body>
    <g:set var="securityService" bean="securityService"/>
      <ul class="nav nav-comandos">
        <li><g:link action="index">Voltar</g:link></li>
      </ul>


    <g:form action="${user.id ? 'update' : 'save'}" class="row" method="post">
      <g:if test="${flash.message}">
          <div class="message col-12" role="status">${flash.message}</div>
      </g:if>
      <g:if test="${flash.error}">
        <ul class="errors col-10" role="alert">
          <li><g:message error="${flash.error}"/></li>
        </ul>
      </g:if>
      <div class="col-md-6">
        <g:if test="${user.id}">
          <input type="hidden" name="id" value="${user.id}"/>
        </g:if>
        <h2 style="margin-bottom: 2.0em;">Dados de acesso</h2>
        <div class="form-group">
          <label for="username">Nome de usu&aacute;rio (login)</label>
          <input type="text" name="username" class="form-control underlined" value="${user.username}" required/>
        </div>
        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" name="email" class="form-control underlined" value="${user.email}"/>
        </div>
        <div class="form-group">
          <label for="enabled">Habilitado</label>
          <g:checkBox name="enabled" value="${user.enabled}"/>
        </div>
        <div class="form-group">
          <label for="email">Perfil</label>
          <g:select class="form-control underlined" from="${perfis}" name="perfil.id" optionKey="id" optionValue="nome" value="${user.perfil?.id}"/>
          
        </div>

        <g:if test="${! user.id}">
          <div class="form-group">
            <label for="email">Senha</label>
            <input type="password" name="password" class="form-control underlined"/>
          </div>
        </g:if>

        <hr style="margin-top: 2.0em; margin-bottom: 2.0em;"/>

        <h2 style="margin-bottom: 2.0em;">Dados pessoais</h2>

        <div class="form-group">
          <label for="nomeCompleto">Nome completo</label>
          <input type="text" name="nomeCompleto" class="form-control underlined" value="${user.nomeCompleto}"/>
        </div>
        <div class="form-group">
          <label for="endereco">Endereço</label>
          <input type="text" name="endereco" class="form-control underlined" value="${user.endereco}"/>
        </div>
        <div class="form-group">
          <label for="cidade">Cidade</label>
          <input type="text" name="cidade" class="form-control underlined" value="${user.cidade}"/>
        </div>
        <div class="form-group">
          <label for="uf">Estado</label>
          <input type="text" name="uf" class="form-control underlined" value="${user.uf}"/>
        </div>
      </div>
      <div class="col-md-6">
        <g:if test="${securityService.currentUser().getAuthorities().find({it.authority == 'ROLE_ADMIN'}) && user.id}">
          <h2>Permiss&otilde;es</h2>
          <div style="max-height: 30vh; height: 30vh; overflow: auto;">
          <g:each in="${roles}" var="role">
            <div >
              <label>
                  <g:checkBox disabled="true" class="checkbox" name="role" value="${role.id}" checked="${user.id && user.authorities?.contains(role)}"/>&nbsp;<span>${role.description}</span>
              </label>
            </div>
          </g:each>
          </div>
        </g:if>
      </div>
      <div class="col-md-12" style="text-align:left; padding: 12px 15px">
        <input type="submit" class="btn btn-primary" value="${user.id ? 'Salvar' : 'Adicionar'}"/>
      </div>
    </g:form>

    <g:if test="${user.id}">
      <h2>Trocar senha</h2>
      <g:form action="updatePassword">
      <input type="hidden" name="id" value="${user.id}"/>
      <div class="form-group">
        <label for="email">Nova senha</label>
        <input type="password" name="password" class="form-control underlined"/>
      </div>
      <input type="submit" class="btn btn-primary" value="Alterar senha"/>
      </g:form>
    </g:if>

  </body>
</html>
