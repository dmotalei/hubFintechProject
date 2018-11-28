import { Injectable } from '@angular/core';
import { PaginacaoPessoa } from 'src/app/pessoa/models/paginacao.pessoa.model';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { PessoaSearchState } from 'src/app/pessoa/models/PessoaSearchState';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class PessoaService {

  private INITIAL_SEARCH_STATE = {
    loading: false,
    error: null,
    pessoas: [],
    paginacaoPessoa: null
  };

  private pessoaSearchStateSubject: BehaviorSubject<PessoaSearchState> = new BehaviorSubject<PessoaSearchState>(this.INITIAL_SEARCH_STATE);

  constructor(
    private httpClient: HttpClient
  ) { } 

  get pessoaSearchState(){
    return this.pessoaSearchStateSubject;
  }

  pesquisar(paginacaoPessoa: PaginacaoPessoa){
     
    let url = `${environment.apiProtocol}${environment.apiHost}:${environment.apiPort}${environment.urlFindPessoa }?`;
    if(paginacaoPessoa.filtro.trim().length > 0){
      url = url + "cpfCnpj=" + paginacaoPessoa.filtro.trim();
    }
    
    if(paginacaoPessoa.paginacao != null){
      if(paginacaoPessoa.paginacao.paginaAtual != null){
        url = url + "&page=" + paginacaoPessoa.paginacao.paginaAtual.toString();
      }
      if(paginacaoPessoa.paginacao.quantidadeItensPorPagina != null){
        url = url + "&size" + paginacaoPessoa.paginacao.quantidadeItensPorPagina.toString();
      }
      if(paginacaoPessoa.paginacao.tipoOrdenacao != null){
        url = url + "&direcaoOrdenacao" + paginacaoPessoa.paginacao.tipoOrdenacao;
      }
      if(paginacaoPessoa.paginacao.ordenacaoPor != null){
        url = url + "&colunaOrdenacao" + paginacaoPessoa.paginacao.ordenacaoPor;
      }
    }

    this.pessoaSearchStateSubject.next({
      loading: true,
      pessoas: [],
      error: null,
      paginacaoPessoa: null
    });
    return this.httpClient.get(url)
      
      .subscribe((pagePessoa: any) => {
        if(pagePessoa == null){
          this.pessoaSearchStateSubject.next({
            loading: false,
            pessoas: [],
            error: null,
            paginacaoPessoa: null
          });
        } else{
          paginacaoPessoa.paginacao.quantidadePaginas = pagePessoa.totalPages;
          paginacaoPessoa.paginacao.quantidadeTotalItens = pagePessoa.totalElements;
          paginacaoPessoa.paginacao.paginaAtual = pagePessoa.pageable.pageNumber + 1;

          this.pessoaSearchStateSubject.next({
            loading: false,
            pessoas: pagePessoa.content,
            error: null,
            paginacaoPessoa: paginacaoPessoa
          });
        }
      }, error => {
        this.pessoaSearchStateSubject.next({
          loading: false,
          pessoas: [],
          error: error.error,
          paginacaoPessoa: null
        });
      });
  }
}
