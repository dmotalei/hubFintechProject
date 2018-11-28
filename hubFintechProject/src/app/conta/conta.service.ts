import { Injectable } from '@angular/core';
import { PaginacaoConta } from 'src/app/conta/models/paginacao.conta.model';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { ContaSearchState } from 'src/app/conta/models/contaSearchState';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';

@Injectable()
export class ContaService {

  private INITIAL_SEARCH_STATE = {
    loading: false,
    error: null,
    contas: [],
    paginacaoConta: null
  };

  private contaSearchStateSubject: BehaviorSubject<ContaSearchState> = new BehaviorSubject<ContaSearchState>(this.INITIAL_SEARCH_STATE);


  constructor(
    private httpClient: HttpClient
  ) { }

  get contaSearchState(){
    return this.contaSearchStateSubject;
  }

  pesquisar(paginacaoConta:PaginacaoConta){
    let url = `${environment.apiProtocol}${environment.apiHost}:${environment.apiPort}${environment.urlFindConta }?`;
    if(paginacaoConta.filtro.trim().length > 0){
      url = url + "numeroConta=" + paginacaoConta.filtro.trim();
    }
    
    if(paginacaoConta.paginacao != null){
      if(paginacaoConta.paginacao.paginaAtual != null){
        url = url + "&page=" + paginacaoConta.paginacao.paginaAtual.toString();
      }
      if(paginacaoConta.paginacao.quantidadeItensPorPagina != null){
        url = url + "&size" + paginacaoConta.paginacao.quantidadeItensPorPagina.toString();
      }
      if(paginacaoConta.paginacao.tipoOrdenacao != null){
        url = url + "&direcaoOrdenacao" + paginacaoConta.paginacao.tipoOrdenacao;
      }
      if(paginacaoConta.paginacao.ordenacaoPor != null){
        url = url + "&colunaOrdenacao" + paginacaoConta.paginacao.ordenacaoPor;
      }
    }

    this.contaSearchStateSubject.next({
      loading: true,
      contas: [],
      error: null,
      paginacaoConta: null
    });
    return this.httpClient.get(url)
      
      .subscribe((pageConta: any) => {
        if(pageConta == null){
          this.contaSearchStateSubject.next({
            loading: false,
            contas: [],
            error: null,
            paginacaoConta: null
          });
        } else{
          paginacaoConta.paginacao.quantidadePaginas = pageConta.totalPages;
          paginacaoConta.paginacao.quantidadeTotalItens = pageConta.totalElements;
          paginacaoConta.paginacao.paginaAtual = pageConta.pageable.pageNumber + 1;

          this.contaSearchStateSubject.next({
            loading: false,
            contas: pageConta.content,
            error: null,
            paginacaoConta: paginacaoConta
          });
        }
      }, error => {
        this.contaSearchStateSubject.next({
          loading: false,
          contas: [],
          error: error.error,
          paginacaoConta: null
        });
      });
  } 
}
