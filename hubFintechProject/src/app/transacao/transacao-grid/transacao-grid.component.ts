import { Component, OnInit } from '@angular/core';
import { Transacao } from '../models/transacao.model';
import { Paginacao } from 'src/app/shared/models/paginacao.model';
import { PaginacaoTransacao } from 'src/app/transacao/models/paginacao.transacao.model';
import { TransacaoService } from 'src/app/transacao/transacao.service';
import { Subscription } from 'rxjs/internal/Subscription';
import { TransacaoSearchState } from 'src/app/transacao/models/transacaoSearchState';
import { ModalTransacaoService } from '../modal-transacao.service';

@Component({
  selector: 'app-transacao-grid',
  templateUrl: './transacao-grid.component.html',
  styleUrls: ['./transacao-grid.component.css']
})
export class TransacaoGridComponent implements OnInit {
  paginaMostrando: TransacaoSearchState;
  subscription: Subscription;

  constructor(
    private transacaoService: TransacaoService
    , private modalService: ModalTransacaoService
  ) { }

  ngOnInit() {
  this.subscription = this.transacaoService.transacaoSearchState.subscribe(res => {
      this.paginaMostrando = res;
   });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  avancarPagina(pagina:number){
    console.log(pagina);

    let paginacaoTransacao = new PaginacaoTransacao();

      
    paginacaoTransacao.filtro = this.paginaMostrando.paginacaoTransacao.filtro;

    paginacaoTransacao.paginacao = new Paginacao();
    paginacaoTransacao.paginacao.paginaAtual = pagina;
    paginacaoTransacao.paginacao.ordenacaoPor = this.paginaMostrando.paginacaoTransacao.paginacao.ordenacaoPor;
    paginacaoTransacao.paginacao.quantidadeItensPorPagina= this.paginaMostrando.paginacaoTransacao.paginacao.quantidadeItensPorPagina;
    paginacaoTransacao.paginacao.quantidadePaginas=null;
    paginacaoTransacao.paginacao.tipoOrdenacao=this.paginaMostrando.paginacaoTransacao.paginacao.tipoOrdenacao;

    console.log(paginacaoTransacao);

    this.transacaoService.pesquisar(paginacaoTransacao);
   
  }

  estornar(transacao:Transacao){
    this.modalService.openModal(transacao);
  }
}
