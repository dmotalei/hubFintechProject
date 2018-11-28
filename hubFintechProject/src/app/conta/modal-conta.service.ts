import { Injectable } from '@angular/core';
import { Conta } from 'src/app/conta/models/conta.model';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { ModalContaSearchState } from 'src/app/conta/models/modalContaState';
import { StatusConta } from './models/statusconta.model';

@Injectable()
export class ModalContaService {
  private INITIAL_MODAL_SEARCH_STATE = {
    exibir: false
    , error: null
    , conta: null
    , statusList: []
    , contaPaiList: []
    , pessoaList: []
  };

  private modalContaStateSubject: BehaviorSubject<ModalContaSearchState> = new BehaviorSubject<ModalContaSearchState>(this.INITIAL_MODAL_SEARCH_STATE);

  constructor(
    private httpClient: HttpClient
  ) { } 

  get modalContaState(){
    return this.modalContaStateSubject;
  }

  openModal(conta:Conta){
    this.modalContaStateSubject.next({
      error: null
      , exibir: false
      , conta: null
      , statusList: []
    });

    let url = `${environment.apiProtocol}${environment.apiHost}:${environment.apiPort}${environment.urlFindlStatusConta }?`;
    //let postData = new FormData();
    //postData.set("conta", conta);

    return this.httpClient.get(url)
      
      .subscribe((response: any) => {
        
        this.modalContaStateSubject.next({
          exibir: true
          , error: null
          , conta: conta
          , statusList: response
        });
      }, error => {
        this.modalContaStateSubject.next({
          exibir: true
          , error: error.error
          , conta: null
          , statusList: []
        });
      });
  }

  closeModal(){
    this.modalContaStateSubject.next({
      error: null
      , exibir: false
      , conta: null
      , statusList: []
    });
  }

  salvar(conta:Conta, statusList:StatusConta[]){
    let url = `${environment.apiProtocol}${environment.apiHost}:${environment.apiPort}${environment.urlSaveConta }?`;
    //let postData = new FormData();
    //postData.set("conta", conta);

    return this.httpClient.post<Conta>(url, conta)
      
      .subscribe((pageConta: any) => {
        this.closeModal();
      }, error => {
        this.modalContaStateSubject.next({
          exibir: true
          , error: error.error
          , conta: conta
          , statusList: statusList
        });
      });
  }
}
