import { PaginacaoPessoa } from "./paginacao.pessoa.model";
import { Pessoa } from "./pessoa.model";


export interface PessoaSearchState{
  loading: boolean;
  error: object;
  pessoas: Pessoa[];
  paginacaoPessoa: PaginacaoPessoa;
}