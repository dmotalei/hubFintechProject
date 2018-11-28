import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/internal/Subscription';
import { ModalContaService } from 'src/app/conta/modal-conta.service';
import { Conta } from 'src/app/conta/models/conta.model';
import { Pessoa } from '../../pessoa/models/pessoa.model';
import { StatusConta } from '../models/statusconta.model';

@Component({
  selector: 'app-modal-conta',
  templateUrl: './modal-conta.component.html',
  styleUrls: ['./modal-conta.component.css']
})
export class ModalContaComponent implements OnInit {

  private error:Object;
  private subscription: Subscription;
  private conta: Conta;
  private exibir:boolean = false;

  private contaPai:string = "";
  private pessoa:string = "";
  
  // ids combos
  private status:string = "-1";
  
  // combos
  private statusList:StatusConta[];

  constructor(
    private modalContaService: ModalContaService
  ) { }

  ngOnInit() {
  this.subscription = this.modalContaService.modalContaState.subscribe(res => {
      this.status = "-1";
      this.contaPai = "";
      this.pessoa = "";

      if(res.conta == null){
        this.conta = new Conta();
        this.conta.numeroConta = "";
        this.conta.saldo = 0.00;
        this.conta.pessoa = null;
        this.conta.statusConta = null;
        this.conta.contaPai = null;
      } else {
        this.conta =  res.conta;
        if(this.conta.statusConta != null
            && this.conta.statusConta.id != null){
              this.status = "" + this.conta.statusConta.id;
        }

        if(this.conta.pessoa != null
            && this.conta.pessoa.cpfCnpj != null){
              this.pessoa = this.conta.pessoa.cpfCnpj;
        }

        if(this.conta.contaPai != null
          && this.conta.contaPai.numeroConta != null){
            this.contaPai = this.conta.contaPai.numeroConta;
        }
      }

      this.statusList = res.statusList;
      this.exibir = res.exibir;
      this.error = res.error;
   });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }


  closeModal(){
    this.modalContaService.closeModal();
  }

  salvar(){
    if(this.conta.contaPai == null){
      this.conta.contaPai = new Conta();
    }
    if(this.conta.pessoa == null){
      this.conta.pessoa = new Pessoa();
    }

    if(this.statusList.length > 0){
      let lista = this.statusList.filter(obj => (obj.id + "" == this.status));
      if(lista.length > 0){
        this.conta.statusConta = lista[0];
      }
    }
    this.conta.pessoa.cpfCnpj = this.pessoa;
    this.conta.contaPai.numeroConta = this.contaPai;
    this.modalContaService.salvar(this.conta, this.statusList);
  }

}
