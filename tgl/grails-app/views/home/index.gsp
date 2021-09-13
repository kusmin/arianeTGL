<!DOCYTPE html>
<html>
  <head>
    <meta name="layout" content="tglMain"/>
    <title>Entrada</title>
  </head>
  <body>

    <h1 class="h1">Plataforma tgl</h1>


    <div class="row">
      <div class="col-md-12">
        <h2 class="h2">Ambiente de execu&ccedil;&atilde;o</h2>
        <table class="table">
          <tbody>
             <tr><td width="15%">Environment:</td><td> ${grails.util.Environment.current.name}</td></tr>
             <tr><td>App profile:</td><td> ${grailsApplication.config.grails?.profile}</td></tr>
             <tr><td>App version:</td><td> <g:meta name="info.app.version"/></td></tr>
             <tr><td>Groovy version:</td><td>${GroovySystem.getVersion()}</td></tr>
             <tr><td>Grails version:</td><td><g:meta name="info.app.grailsVersion"/></td></tr>
             <tr><td>JVM version:</td><td>${System.getProperty('java.version')}</td></tr>
             <tr><td>Reloading active:</td><td>${grails.util.Environment.reloadingAgentEnabled}</td></tr>
          </tbody>
        </table>

        <h2 class="h2">Artefatos</h2>
        <table class="table">

                <tr><td width="15%">Controladores:</td><td> ${grailsApplication.controllerClasses.size()}</td></tr>
                <tr><td>Classes de domínio:</td><td> ${grailsApplication.domainClasses.size()}</td></tr>
                <tr><td>Serviços:</td><td> ${grailsApplication.serviceClasses.size()}</td></tr>
                <tr><td>Tag Libs:</td><td> ${grailsApplication.tagLibClasses.size()}</td></tr>

        </table>

        <h2 class="h2">Plugins</h2>
        <table class="table">
          <thead>
            <tr>
              <th width="15%">Nome</th>
              <th>Versão</th>
            </tr>
          </thead>
          <tbody>
          <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
              <tr><td>${plugin.name}</td><td>${plugin.version}</td></tr>
          </g:each>
          </tbody>
        </table>
      </div>

    </div>
    <div style="text-align:center;">
      <a href="http://www.blogdopensadormoderno.com.br"><asset:image src="logo.png" style="max-width:128px;"/></a>
    </div>
  </body>
</html>
