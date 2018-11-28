import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { TransacaoSearchState } from 'src/app/transacao/models/transacaoSearchState';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { PaginacaoTransacao } from 'src/app/transacao/models/paginacao.transacao.model';

@Injectable()
export class TransacaoService {

  private INITIAL_SEARCH_STATE = {
    loading: false,
    error: null,
    transacoes: [],
    paginacaoTransacao: null
  };

  private transacaoSearchStateSubject: BehaviorSubject<TransacaoSearchState> = new BehaviorSubject<TransacaoSearchState>(this.INITIAL_SEARCH_STATE);


  constructor(
    private httpClient: HttpClient
  ) { }

  get transacaoSearchState(){
    return this.transacaoSearchStateSubject;
  }

  pesquisar(paginacaoTransacao:PaginacaoTransacao){
    let url = `${environment.apiProtocol}${environment.apiHost}:${environment.apiPort}${environment.urlFindTransacao }?`;
    if(paginacaoTransacao.filtro.trim().length > 0){
      url = url + "numeroConta=" + paginacaoTransacao.filtro.trim();
    }
    
    if(paginacaoTransacao.paginacao != null){
      if(paginacaoTransacao.paginacao.paginaAtual != null){
        url = url + "&page=" + paginacaoTransacao.paginacao.paginaAtual.toString();
      }
      if(paginacaoTransacao.paginacao.quantidadeItensPorPagina != null){
        url = url + "&size" + paginacaoTransacao.paginacao.quantidadeItensPorPagina.toString();
      }
      if(paginacaoTransacao.paginacao.tipoOrdenacao != null){
        url = url + "&direcaoOrdenacao" + paginacaoTransacao.paginacao.tipoOrdenacao;
      }
      if(paginacaoTransacao.paginacao.ordenacaoPor != null){
        url = url + "&colunaOrdenacao" + paginacaoTransacao.paginacao.ordenacaoPor;
      }
    }

    this.transacaoSearchStateSubject.next({
      loading: true,
      transacoes: [],
      error: null,
      paginacaoTransacao: null
    });
    return this.httpClient.get(url)
      
      .subscribe((pageTransacao: any) => {
        if(pageTransacao == null){
          this.transacaoSearchStateSubject.next({
            loading: false,
            transacoes: [],
            error: null,
            paginacaoTransacao: null
          });
        } else{
          paginacaoTransacao.paginacao.quantidadePaginas = pageTransacao.totalPages;
          paginacaoTransacao.paginacao.quantidadeTotalItens = pageTransacao.totalElements;
          paginacaoTransacao.paginacao.paginaAtual = pageTransacao.pageable.pageNumber + 1;

          this.transacaoSearchStateSubject.next({
            loading: false,
            transacoes: pageTransacao.content,
            error: null,
            paginacaoTransacao: paginacaoTransacao
          });
        }
      }, error => {
        this.transacaoSearchStateSubject.next({
          loading: false,
          transacoes: [],
          error: error.error,
          paginacaoTransacao: null
        });
      });
  } 
}
