import { Injectable } from '@angular/core';
import { Transacao } from './models/transacao.model';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { ModalTransacaoSearchState } from 'src/app/transacao/models/modalTransacaoState';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable()
export class ModalTransacaoService {

  private INITIAL_MODAL_SEARCH_STATE = {
    exibir: false
    , error: null
    , transacao: null
  };

  private modalTransacaoStateSubject: BehaviorSubject<ModalTransacaoSearchState> = new BehaviorSubject<ModalTransacaoSearchState>(this.INITIAL_MODAL_SEARCH_STATE);

  constructor(
    private httpClient: HttpClient
  ) { } 

  get modalTransacaoState(){
    return this.modalTransacaoStateSubject;
  }

  openModal(transacao:Transacao){
    this.modalTransacaoStateSubject.next({
      error: null
      , exibir: true
      , transacao: transacao
    });
  }

  closeModal(){
    this.modalTransacaoStateSubject.next({
      error: null
      , exibir: false
      , transacao: null
    });
  }

  salvar(transacao:Transacao){
    let url = `${environment.apiProtocol}${environment.apiHost}:${environment.apiPort}${environment.urlSaveTransacao }?`;

    return this.httpClient.post<Transacao>(url, transacao)
      
      .subscribe((response: any) => {
        this.closeModal();
      }, error => {
        this.modalTransacaoStateSubject.next({
          exibir: true
          , error: error.error
          , transacao: transacao
        });
      });
  }

  estornar(transacao:Transacao){
    let url = `${environment.apiProtocol}${environment.apiHost}:${environment.apiPort}${environment.urlEstornoTransacao }?`;
    let postData = new FormData();
    postData.set("idTransacao", "" + transacao.id);
    if(transacao.codigoEstornoAporte != null
        && transacao.codigoEstornoAporte.trim() != ""){
          postData.set("codigoEstorno", transacao.codigoEstornoAporte);
    }


    return this.httpClient.post(url, postData)
      
      .subscribe((response: any) => {
        this.closeModal();
      }, error => {
        this.modalTransacaoStateSubject.next({
          exibir: true
          , error: error.error
          , transacao: transacao
        });
      });
  }
}
