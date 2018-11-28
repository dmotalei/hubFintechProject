import { Component, OnInit } from '@angular/core';
import { ModalPessoaService } from 'src/app/pessoa/modal-pessoa.service';
import { Subscription } from 'rxjs/internal/Subscription';
import { Pessoa } from '../models/pessoa.model';
import { PessoaFiltroComponent } from '../pessoa-filtro/pessoa-filtro.component';

@Component({
  selector: 'app-modal-pessoa',
  templateUrl: './modal-pessoa.component.html',
  styleUrls: ['./modal-pessoa.component.css']
})
export class ModalPessoaComponent implements OnInit {

  exibir:boolean = false;
  tipoPessoa:string = "0";
  error:Object;
  subscription: Subscription;


  cpfCnpj:string;
  dataNasCimento:Date;
  nome:string;
  razaoSocial:string;

  constructor(
    private modalPessoaService: ModalPessoaService
  ) { }

  ngOnInit() {
  this.subscription = this.modalPessoaService.modalPessoaState.subscribe(res => {
      this.exibir = res.exibir;
      this.error = res.error;
      this.tipoPessoa = "0";
      
      this.cpfCnpj= "";
      this.dataNasCimento= new Date();
      this.nome= "";
      this.razaoSocial= "";
   });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }


  closeModal(){
    this.modalPessoaService.closeModal();
  }

  salvar(){
    this.error = null;

    let pessoa:Pessoa = new Pessoa();
    pessoa.cpfCnpj = this.cpfCnpj;
    pessoa.nome = this.nome;
    if(this.tipoPessoa == '0'){
      pessoa.dataNascimento = this.dataNasCimento;
      pessoa.tipoPessoa = false;
    } else{
      pessoa.razaoSocial = this.razaoSocial;
      pessoa.tipoPessoa = true;
    }

    this.modalPessoaService.salvar(pessoa);
  }
}
