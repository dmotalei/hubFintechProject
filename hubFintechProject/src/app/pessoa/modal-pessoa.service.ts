import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { ModalPessoaSearchState } from './models/modalPessoaState';
import { Pessoa } from 'src/app/pessoa/models/pessoa.model';

@Injectable()
export class ModalPessoaService {

  private INITIAL_MODAL_SEARCH_STATE = {
    exibir: false,
    error: null,
  };

  private modalPessoaStateSubject: BehaviorSubject<ModalPessoaSearchState> = new BehaviorSubject<ModalPessoaSearchState>(this.INITIAL_MODAL_SEARCH_STATE);

  constructor(
    private httpClient: HttpClient
  ) { } 

  get modalPessoaState(){
    return this.modalPessoaStateSubject;
  }

  openModal(){
    this.modalPessoaStateSubject.next({
      error: null,
      exibir: true
    });
  }

  closeModal(){
    this.modalPessoaStateSubject.next({
      error: null,
      exibir: false
    });
  }

  salvar(pessoa:Pessoa){
    let url = `${environment.apiProtocol}${environment.apiHost}:${environment.apiPort}${environment.urlSavePessoa }?`;
    //let postData = new FormData();
    //postData.set("pessoa", pessoa);

    return this.httpClient.post<Pessoa>(url, pessoa)
      
      .subscribe((pagePessoa: any) => {
        this.closeModal();
      }, error => {
        this.modalPessoaStateSubject.next({
          exibir: true
          , error: error.error
        });
      });
  }
}
