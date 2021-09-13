<aside class="sidebar">
    <div class="sidebar-container">
        <div class="sidebar-heade row ">
            <div class="brand rounded mx-auto d-block">  
                <g:link controller="home" action="index" >
                    <asset:image src="logo.png" class="logo-menu img-thumbnail " width="50px" heigth="50px"/>
                </g:link>
            </div>
        </div>
        <nav class="menu row p-3 mt-3">



            <ul class="sidebar-menu metismenu mt-3" id="sidebar-menu">

              <g:set var="layoutService" bean="layoutService"/>
              <g:each in="${menus}" var="menu">
                <g:if test="${layoutService.shouldRender(menu)}">
                  <g:set var="hasChildren" value="${tgl.layout.MenuItem.countByParent(menu) > 0}"/>
                  <li>
                    <g:if test="${hasChildren}">
                      <a href="">
                        <i class="${menu.icon}"> ${menu.title}</i>
                        <i class="fa arrow"></i>
                      </a>
                      <ul class="sidebar-nav">
                        <g:each in="${tgl.layout.MenuItem.findAllByParent(menu, [sort:'order'])}" var="subMenu">
                            
                          <%-- <g:if test="${subMenu?.podeRenderizar()}"> --%>
                            <li>
                                <g:link action="${subMenu.action}" controller="${subMenu.controller}">
                                <i class="${subMenu.icon}"></i> ${subMenu.title}
                                </g:link>
                            </li>
                        <%-- </g:if> --%>
                        </g:each>
                      </ul>
                    </g:if>
                    <g:else>
                     <g:link action="${menu.action}" controller="${menu.controller}">
                       <i class="${menu.icon}"> ${menu.title}</i>
                     </g:link>
                    </g:else>
                  </li>
                </g:if>
              </g:each><%-- fim de iteração por menus pai --%>
            </ul>
        </nav>
    </div>
    <footer class="sidebar-footer">
        <ul class="sidebar-menu metismenu" id="customize-menu">
            <li>
                <ul>
                    <li class="customize">
                        <div class="customize-item">
                            <div class="row customize-header">
                                <div class="col-4"> </div>
                                <div class="col-4">
                                    <label class="title">Fixo</label>
                                </div>
                                <div class="col-4">
                                    <label class="title">Est&aacute;tico</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-4">
                                    <label class="title">Menu:</label>
                                </div>
                                <div class="col-4">
                                    <label>
                                        <input class="radio" type="radio" name="sidebarPosition" value="sidebar-fixed">
                                        <span></span>
                                    </label>
                                </div>
                                <div class="col-4">
                                    <label>
                                        <input class="radio" type="radio" name="sidebarPosition" value="">
                                        <span></span>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-4">
                                    <label class="title">Cabe&ccedil;alho:</label>
                                </div>
                                <div class="col-4">
                                    <label>
                                        <input class="radio" type="radio" name="headerPosition" value="header-fixed">
                                        <span></span>
                                    </label>
                                </div>
                                <div class="col-4">
                                    <label>
                                        <input class="radio" type="radio" name="headerPosition" value="">
                                        <span></span>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-4">
                                    <label class="title">Rodap&eacute;:</label>
                                </div>
                                <div class="col-4">
                                    <label>
                                        <input class="radio" type="radio" name="footerPosition" value="footer-fixed">
                                        <span></span>
                                    </label>
                                </div>
                                <div class="col-4">
                                    <label>
                                        <input class="radio" type="radio" name="footerPosition" value="">
                                        <span></span>
                                    </label>
                                </div>
                            </div>
                        </div>

                    </li>
                </ul>
                <a href="">
                    <i class="fa fa-cog"></i> Customiza&ccedil;&atilde;o </a>
            </li>
        </ul>
    </footer>
</aside>
