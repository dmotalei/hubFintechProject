import { Component, OnInit } from '@angular/core';
import { ContaService } from 'src/app/conta/conta.service';
import { Subscription } from 'rxjs/internal/Subscription';
import { ContaSearchState } from 'src/app/conta/models/contaSearchState';
import { PaginacaoConta } from 'src/app/conta/models/paginacao.conta.model';
import { Paginacao } from 'src/app/shared/models/paginacao.model';
import { Conta } from '../models/conta.model';
import { ModalContaService } from 'src/app/conta/modal-conta.service';

@Component({
  selector: 'app-conta-grid',
  templateUrl: './conta-grid.component.html',
  styleUrls: ['./conta-grid.component.css']
})
export class ContaGridComponent implements OnInit {

  paginaMostrando: ContaSearchState;
  subscription: Subscription;

  constructor(
    private contaService: ContaService
    , private modalContaService: ModalContaService
  ) { }

  ngOnInit() {
  this.subscription = this.contaService.contaSearchState.subscribe(res => {
      this.paginaMostrando = res;
   });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  avancarPagina(pagina:number){
    console.log(pagina);

    let paginacaoConta = new PaginacaoConta();

      
    paginacaoConta.filtro = this.paginaMostrando.paginacaoConta.filtro;

    paginacaoConta.paginacao = new Paginacao();
    paginacaoConta.paginacao.paginaAtual = pagina;
    paginacaoConta.paginacao.ordenacaoPor = this.paginaMostrando.paginacaoConta.paginacao.ordenacaoPor;
    paginacaoConta.paginacao.quantidadeItensPorPagina= this.paginaMostrando.paginacaoConta.paginacao.quantidadeItensPorPagina;
    paginacaoConta.paginacao.quantidadePaginas=null;
    paginacaoConta.paginacao.tipoOrdenacao=this.paginaMostrando.paginacaoConta.paginacao.tipoOrdenacao;

    console.log(paginacaoConta);

    this.contaService.pesquisar(paginacaoConta);
   
  }

  editar(conta:Conta){
    this.modalContaService.openModal(conta);
  }
}