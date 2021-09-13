<!doctype html>
<html class="no-js" lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>tgl</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Place favicon.ico in the root directory -->
        <input type="hidden" id="appContext" value="${createLink(uri:'/')}"/>
        <asset:stylesheet src="tgl.css"/>


    </head>
    <body>
        <g:set var="securityService" bean="securityService"/>
        <div class="main-wrapper">
            <div class="app" id="app">
                <header class="header">
                    <div class="header-block header-block-collapse d-lg-none d-xl-none" style="width:auto;">
                        <button class="collapse-btn" id="sidebar-collapse-btn">
                            <i class="fa fa-bars"></i>
                        </button>
                    </div>
                    <div style="color:white; font-size: 1em;" class="visible-xs"><g:layoutTitle default="Inclua a tag &lt;title&gt; na página" /></div>
                    <div style="padding-left: 2.0em; color:white;" class="hidden-xs h3"><g:layoutTitle default="Inclua a tag &lt;title&gt; na página" /></div>
                    <div class="header-block header-block-buttons">
                    </div>
                    <div class="header-block header-block-nav">
                        <ul class="nav-profile">
                            <li class="profile dropdown">
                                <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                                    <span class="name"> <tglLayout:username/> </span>
                                </a>
                                <div class="dropdown-menu profile-dropdown-menu" aria-labelledby="dropdownMenu1">
                                    <g:link action="show" id="${securityService.currentUser().id}" controller="users" class="dropdown-item">
                                        <i class="fa fa-user icon"></i> Perfil
                                    </g:link>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="/logout">
                                        <i class="fa fa-power-off icon"></i> Sair
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </header>

                <!-- Side menu -->
                <tglLayout:sideMenu/>

                <!-- fim de Side menu -->

                <div class="sidebar-overlay" id="sidebar-overlay"></div>
                <div class="sidebar-mobile-menu-handle" id="sidebar-mobile-menu-handle"></div>
                <div class="mobile-menu-handle"></div>
                <article class="content dashboard-page">

                  <%-- exposição de mensagens --%>
                  <g:if test="${flash.message}">
                    <div class="panel panel-default">
                      ${raw(flash.message)}
                    </div>
                  </g:if>

                  <g:layoutBody/>
                </article>
                <footer class="footer">
                    <div class="footer-block">

                    </div>
                    <div class="footer-block author">
                        <ul>
                            <li style="color:white"> Criado por
                                <a style="color:white" href="http://www.blogdopensadormoderno.com.br">Renan</a>
                            </li>
                            <li>
                                Vers&atilde;o: <g:meta name="info.app.version"/>
                            </li>
                        </ul>
                    </div>
                </footer>


            </div>
        </div>
        <!-- Reference block for JS -->
        <div class="ref" id="ref">
            <div class="color-primary"></div>
            <div class="chart">
                <div class="color-primary"></div>
                <div class="color-secondary"></div>
            </div>
        </div>

        <asset:javascript src="tgl.js"/>


    </body>
</html>
