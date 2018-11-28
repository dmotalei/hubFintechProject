import { TransacaoService } from "../transacao.service";
import { Component, OnInit } from "@angular/core";
import { PaginacaoTransacao } from "src/app/transacao/models/paginacao.transacao.model";
import { Paginacao } from "src/app/shared/models/paginacao.model";
import { environment } from "src/environments/environment";
import { ModalTransacaoService } from "src/app/transacao/modal-transacao.service";

@Component({
  selector: 'app-transacao-filtro',
  templateUrl: './transacao-filtro.component.html',
  styleUrls: ['./transacao-filtro.component.css']
})
export class TransacaoFiltroComponent implements OnInit {

  private numeroConta:string = "";


  constructor(
    private service:TransacaoService
    , private modalService:ModalTransacaoService
  ) { }

  ngOnInit() {
  }

  search(){
    let paginacaoTransacao = new PaginacaoTransacao();
    paginacaoTransacao.filtro = this.numeroConta;

    paginacaoTransacao.paginacao = new Paginacao();
    paginacaoTransacao.paginacao.paginaAtual = null;
    paginacaoTransacao.paginacao.ordenacaoPor = null;
    paginacaoTransacao.paginacao.quantidadeItensPorPagina= environment.qtdResultadosPagina;
    paginacaoTransacao.paginacao.quantidadePaginas=null;
    paginacaoTransacao.paginacao.tipoOrdenacao=environment.tipoOrdenacao;

    this.service.pesquisar(paginacaoTransacao);
      
  }

  openModal(){
      this.modalService.openModal(null); 
  }

}
 