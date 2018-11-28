import { Component, OnInit } from '@angular/core';
import { ModalContaService } from '../modal-conta.service';
import { environment } from 'src/environments/environment';
import { Paginacao } from 'src/app/shared/models/paginacao.model';
import { PaginacaoConta } from 'src/app/conta/models/paginacao.conta.model';
import { ContaService } from '../conta.service';

@Component({
  selector: 'app-conta-filtro',
  templateUrl: './conta-filtro.component.html',
  styleUrls: ['./conta-filtro.component.css']
})
export class ContaFiltroComponent implements OnInit {

  private numeroConta:string = "";


  constructor(
    private service:ContaService
    , private modalService:ModalContaService
  ) { }

  ngOnInit() {
  }

  search(){
    let paginacaoConta = new PaginacaoConta();
    paginacaoConta.filtro = this.numeroConta;

    paginacaoConta.paginacao = new Paginacao();
    paginacaoConta.paginacao.paginaAtual = null;
    paginacaoConta.paginacao.ordenacaoPor = null;
    paginacaoConta.paginacao.quantidadeItensPorPagina= environment.qtdResultadosPagina;
    paginacaoConta.paginacao.quantidadePaginas=null;
    paginacaoConta.paginacao.tipoOrdenacao=environment.tipoOrdenacao;

    this.service.pesquisar(paginacaoConta);
      
  }

  openModal(){
      this.modalService.openModal(null); 
  }

}
 