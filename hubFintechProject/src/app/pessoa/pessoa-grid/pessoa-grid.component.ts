import { Component, OnInit } from '@angular/core';
import { PessoaSearchState } from 'src/app/pessoa/models/PessoaSearchState';
import { Subscription } from 'rxjs/internal/Subscription';
import { PessoaService } from 'src/app/pessoa/pessoa.service';
import { PaginacaoPessoa } from 'src/app/pessoa/models/paginacao.pessoa.model';
import { Paginacao } from 'src/app/shared/models/paginacao.model';

@Component({
  selector: 'app-pessoa-grid',
  templateUrl: './pessoa-grid.component.html',
  styleUrls: ['./pessoa-grid.component.css']
})
export class PessoaGridComponent implements OnInit {

  paginaMostrando: PessoaSearchState;
  subscription: Subscription;

  constructor(
    private pessoaService: PessoaService
  ) { }

  ngOnInit() {
  this.subscription = this.pessoaService.pessoaSearchState.subscribe(res => {
      this.paginaMostrando = res;
   });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  avancarPagina(pagina:number){
    console.log(pagina);

    let paginacaoPessoa = new PaginacaoPessoa();

      
    paginacaoPessoa.filtro = this.paginaMostrando.paginacaoPessoa.filtro;

    paginacaoPessoa.paginacao = new Paginacao();
    paginacaoPessoa.paginacao.paginaAtual = pagina;
    paginacaoPessoa.paginacao.ordenacaoPor = this.paginaMostrando.paginacaoPessoa.paginacao.ordenacaoPor;
    paginacaoPessoa.paginacao.quantidadeItensPorPagina= this.paginaMostrando.paginacaoPessoa.paginacao.quantidadeItensPorPagina;
    paginacaoPessoa.paginacao.quantidadePaginas=null;
    paginacaoPessoa.paginacao.tipoOrdenacao=this.paginaMostrando.paginacaoPessoa.paginacao.tipoOrdenacao;

    console.log(paginacaoPessoa);

    this.pessoaService.pesquisar(paginacaoPessoa);
   
  }
}