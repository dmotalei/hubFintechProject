import { Component, OnInit } from '@angular/core';
import { PessoaService } from '../pessoa.service';
import { PaginacaoPessoa } from '../models/paginacao.pessoa.model';
import { Paginacao } from '../../shared/models/paginacao.model';
import { environment } from 'src/environments/environment';
import { ModalPessoaService } from 'src/app/pessoa/modal-pessoa.service';

@Component({
  selector: 'app-pessoa-filtro',
  templateUrl: './pessoa-filtro.component.html',
  styleUrls: ['./pessoa-filtro.component.css']
})
export class PessoaFiltroComponent implements OnInit {

  cpfCnpj:string = "";

  constructor(
    private service:PessoaService
    , private modalService: ModalPessoaService
  ) { }

  ngOnInit() {
  }

  search(){

    let paginacaoPessoa = new PaginacaoPessoa();
    paginacaoPessoa.filtro = this.cpfCnpj;

    paginacaoPessoa.paginacao = new Paginacao();
    paginacaoPessoa.paginacao.paginaAtual = null;
    paginacaoPessoa.paginacao.ordenacaoPor = null;
    paginacaoPessoa.paginacao.quantidadeItensPorPagina= environment.qtdResultadosPagina;
    paginacaoPessoa.paginacao.quantidadePaginas=null;
    paginacaoPessoa.paginacao.tipoOrdenacao=environment.tipoOrdenacao;

    this.service.pesquisar(paginacaoPessoa);
      
  }

  openModal(){
      this.modalService.openModal();
  }
}
