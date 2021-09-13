<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="tglMain"/>
    <title>Gestão de usuários</title>
  </head>
  <body>
    <sec:ifAnyGranted roles="ROLE_ADMIN">
      <ul class="nav nav-comandos">
        <li><g:link action="create">Criar usuário</g:link></li>
      </ul>
    </sec:ifAnyGranted>
    <g:form action="search" class="form-inline">
      <div class="form-group">
        <input type="text" name="username" value="${params.username}" class="form-control underlined" placeholder="Nome do usuário"/>
      </div>
      <div class="form-group">
        &nbsp;&nbsp;
        <input type="text" name="nomeCompleto" value="${params.nomeCompleto}" class="form-control underlined" placeholder="Nome completo"/>
      </div>
      <div class="form-group">
        Perfil:&nbsp;
        <g:select from="${perfis}" class="form-control underlined" optionKey="id" optionValue="nome"  value="${params.perfil}" name="perfil" noSelection="${['':'Todos']}"/>
      </div>
       <div class="form-group">
        Ativo:&nbsp;
        <g:select from="${["Ativos", "Inativos"]}" class="form-control underlined"  value="${params.enabled}" name="enabled" noSelection="${['':'Todos']}"/>
      </div>
      <div class="form-group">
        &nbsp;&nbsp;<input type="submit" value="Buscar" class="btn btn-primary"/>
      </div>
    </g:form>
    <table class="table">
      <thead>
        <tr>
          <th>Nome do usu&aacute;rio</th>
          <th>Nome completo</th>
          <th>Perfil</th>
          <th>Ativo</th>
        </tr>
      </thead>
      <tbody>
        <g:each in="${users}" var="user">
        <tr>
          <td><g:link action="show" id="${user.id}">${user.username}</g:link></td>
          <td>${user.nomeCompleto}</td>
          <td>${user.perfil.nome}</td>
          <td>${user.enabled ? 'Sim' : 'Não'}</td>
        </tr>
        </g:each>
      </tbody>
    </table>
  </body>
</html>
