<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>

<header class="header bg-light mb-4">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light navbar-static-top  bg-light" role="navigation">
            <g:link class="navbar-brand media mt-3" controller="proposta" action="index"><asset:image class="img-fluid" src="tgl.png" alt="tgl Logo" width= "100" height="50"/></g:link>
            <button class="navbar-toggler " type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon "></span>
            </button>

            <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
                <ul class="nav navbar-nav ml-auto text-success">
                    </li ><g:link class="text-success" controller="proposta" action="index">Propostas</g:link></li>
                    </li><g:link class="text-success" controller="cliente" action="index">Clientes</g:link></li>
                    </li><g:link class="text-success" controller="consultor" action="index">Consultores</g:link></li>
                    </li><g:link class="text-success" controller="gerente" action="index">Gerente</g:link></li>
                    </li><g:link class="text-success" controller="seguradora" action="index">Seguradoras</g:link></li>
                </ul>
            </div>

        </nav>
    </div>
</header>
<div class="p-5">
    <g:layoutBody/>
</div>
<div class="footer row" role="contentinfo">
    
    </div>
</div>


<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>
