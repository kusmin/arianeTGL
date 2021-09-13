[
  {
    "title":"Home",
    "code":"home",
    "icon":"fa fa-address-card",
    "roles":"ROLE_USER",
    "controller":"home",
    "action":"index"
  },
  {
    "title":"Administração",
    "code":"admin",
    "icon":"fa fa-wrench",
    "roles":"ROLE_ADMIN",
    "itens":[
      {
        "title":"Configurações gerais",
        "code":"configs",
        "icon":"fa fa-cogs",
        "roles":"ROLE_ADMIN",
        "controller":"configuration",
        "action":"index"
      },
      {
        "title":"Usuários",
        "code":"users",
        "icon":"fa fa-address-card",
        "roles":"ROLE_ADMIN",
        "controller":"user",
        "action":"index"
      },
      {
        "title":"Perfis",
        "code":"perfis",
        "icon":"fa fa-address-card",
        "roles":"ROLE_ADMIN",
        "action":"index",
        "controller":"perfil"
      }
    ]
  },{
    "title":"Cadastro de Proposta",
    "code":"cadastro",
    "icon":"fa fa-wrench",
    "roles":"ROLE_ADMIN",
    "itens":
    [
      {
        "title":"Clientes",
        "code":"cliente",
        "icon":"fa fa-cogs",
        "roles":"ROLE_ADMIN",
        "controller":"cliente",
        "action":"index"
      },
      {
        "title":"Consultores",
        "code":"consultor",
        "icon":"fa fa-address-card",
        "roles":"ROLE_ADMIN",
        "controller":"consultor",
        "action":"index"
      },
      {
        "title":"Gerentes",
        "code":"gerente",
        "icon":"fa fa-address-card",
        "roles":"ROLE_ADMIN",
        "action":"index",
        "controller":"gerente"
      },
      {
        "title":"Propostas",
        "code":"propostas",
        "icon":"fa fa-address-card",
        "roles":"ROLE_ADMIN",
        "controller":"proposta",
        "action":"index"
      },
      {
        "title":"Seguradoras",
        "code":"seguradoras",
        "icon":"fa fa-address-card",
        "roles":"ROLE_ADMIN",
        "action":"index",
        "controller":"seguradora"
      }
    ]
  }
  
]
