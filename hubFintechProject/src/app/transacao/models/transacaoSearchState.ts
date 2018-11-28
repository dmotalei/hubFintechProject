import { PaginacaoTransacao } from "./paginacao.transacao.model";
import { Transacao } from "src/app/transacao/models/transacao.model";


export interface TransacaoSearchState{
  loading: boolean;
  error: object;
  transacoes: Transacao[];
  paginacaoTransacao: PaginacaoTransacao;
}