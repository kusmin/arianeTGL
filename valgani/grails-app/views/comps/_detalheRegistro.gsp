<h2 class="labelSessao">Sobre o registro</h2>
<tglLayout:display label="Autor" value="${objeto.autor}"/>
<tglLayout:display label="Criado em" value="${objeto.dataCriacao != null ? g.formatDate(date:objeto.dataCriacao, format:'dd/MM/yyyy HH:mm:ss') : null}"/> 
<tglLayout:display label="Editor" value="${objeto.editor}"/>
<tglLayout:display label="Editado em" value="${objeto.dataEdicao != null ? g.formatDate(date:objeto.dataEdicao, format:'dd/MM/yyyy HH:mm:ss') : null}"/>