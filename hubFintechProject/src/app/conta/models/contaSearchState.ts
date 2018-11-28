import { PaginacaoConta } from "./paginacao.conta.model";
import { Conta } from "./conta.model";


export interface ContaSearchState{
  loading: boolean;
  error: object;
  contas: Conta[];
  paginacaoConta: PaginacaoConta;
}