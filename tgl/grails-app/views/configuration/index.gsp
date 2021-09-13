<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="tglMain"/>
    <title>Configurações gerais</title>
  </head>
  <body>

    <div id="appConfig">
      <form action="search" class="form-inline" v-on:submit.prevent>
        <div class="form-group" Style="padding-top: 6em;">
          <input type="text" name="code" class="form-control underlined" placeholder="Código" v-model="code"/>
        </div>
        <div class="form-group" Style="padding-top: 6em;">
          <input type="submit" class="btn btn-default" value="Buscar" v-on:click="search"/>
        </div>
      </form>
      <table class="table">
        <thead>
          <tr>
            <th>Código</th>
            <th>Valor</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="config in configurations">
            <td>{{config.code}}</td>
            <td><input type="text" v-model="config.value" class="form-control underlined"/></td>
            <td><input type="button" value="Salvar" class="btn btn-primary" v-on:click="update(config)"/></td>
          </tr>
        </tbody>
      </table>
    </div>

    <asset:javascript src="vue.js"/>
    <asset:javascript src="axios.js"/>
    <script type="text/javascript">
      new Vue({
        el:'#appConfig',
        data: function() {
          return {
            code:"",
            context:document.getElementById('appContext').value,
            configurations:[]
          }
        },
        methods:{

          search:function() {
            var $this = this
            axios.get(this.context + 'configuration/search', {params:{code:this.code}})
              .then(function(response) {

                $this.configurations = response.data

              })
              .catch(function (error) {
                alert("Erro ao obter configurações, procure o suporte");
              })
          },

          update:function(config) {
            var $this = this
          
            axios(
              {
                method:"post",
                data:{code:config.code, value:config.value},
                url:this.context + 'configuration/update'
              }
            )
            .then(function(response) {
              alert("Configuração alterada com sucesso")
            })
            .catch(function(error) {
              alert("Erro ao atualizar configuração")
            })
          }
        }
      })
    </script>
  </body>
</html>
