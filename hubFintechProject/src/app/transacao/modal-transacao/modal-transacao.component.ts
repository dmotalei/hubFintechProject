import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/internal/Subscription';
import { ModalTransacaoService } from 'src/app/transacao/modal-transacao.service';
import { Transacao } from 'src/app/transacao/models/transacao.model';
import { Conta } from '../../conta/models/conta.model';

@Component({
  selector: 'app-modal-transacao',
  templateUrl: './modal-transacao.component.html',
  styleUrls: ['./modal-transacao.component.css']
})
export class ModalTransacaoComponent implements OnInit {
  private error:Object;
  private subscription: Subscription;
  private exibir:boolean = false;
  private tipoTransacao:string = "0";
  private transacao:Transacao;
  private transferencia:boolean =true;

  constructor(
    private modalTransacaoService: ModalTransacaoService
  ) { }

  ngOnInit() {
    this.subscription = this.modalTransacaoService.modalTransacaoState.subscribe(res => {
        this.transferencia = true;
        if(res.transacao == null){
          this.transacao = new Transacao();
          this.transacao.contaDestino = new Conta();
          this.transacao.contaDestino.numeroConta = "";
          this.transacao.contaRemetente = new Conta();
          this.transacao.contaRemetente.numeroConta = "";
          this.transacao.valor = 0.00;
          this.tipoTransacao = "0";
        } else{
          this.transacao = res.transacao;
          if(this.transacao.flagAporte != null
              && this.transacao.flagAporte){
            this.tipoTransacao = "1";
          } else{
            this.tipoTransacao = "0";
          }

          if(res.transacao.id != null){
            this.transferencia = false;
          }
        }
      
        this.exibir = res.exibir;
        this.error = res.error;
    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }


  closeModal(){
    this.modalTransacaoService.closeModal();
  }

  salvar(){

    if(this.tipoTransacao == "1"){
      this.transacao.flagAporte = true;
      this.transacao.contaRemetente.numeroConta = "";
    } else{
      this.transacao.flagAporte = false;
    }

    this.modalTransacaoService.salvar(this.transacao);
  }

  estornar(){
    this.modalTransacaoService.estornar(this.transacao);
  }


}
