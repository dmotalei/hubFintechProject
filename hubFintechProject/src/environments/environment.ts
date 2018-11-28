// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false
  , apiProtocol: "http://"
  , apiHost: "localhost"
  , apiPort: "8081"
  , apiPrefix: ""

  // Propriedades de paginacao
  , qtdResultadosPagina: 10
  , tipoOrdenacao: "ASC"

  // Urls servicos de gestao carteira
  , urlFindPessoa: "/api/pessoa/find"
  , urlSavePessoa: "/api/pessoa"


  , urlFindConta: "/api/conta/find"
  , urlSaveConta: "/api/conta"
  , urlFindlStatusConta: "/api/statusConta/find"

  ,urlFindTransacao: "/api/transacao/find"
  ,urlSaveTransacao: "/api/transacao"
  ,urlEstornoTransacao: "/api/transacao/estornar"
};

