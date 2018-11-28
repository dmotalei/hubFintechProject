import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PessoaMainComponent } from './pessoa-main/pessoa-main.component';
import { PessoaRoutingModule } from './pessoa-routing.module';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../shared/shared.module';
import { PessoaFiltroComponent } from './pessoa-filtro/pessoa-filtro.component';
import { PessoaGridComponent } from './pessoa-grid/pessoa-grid.component';
import { PessoaService } from 'src/app/pessoa/pessoa.service';
import { ModalPessoaComponent } from './modal-pessoa/modal-pessoa.component';
import { ModalPessoaService } from './modal-pessoa.service';

@NgModule({
  imports: [
    CommonModule
    , FormsModule
    , PessoaRoutingModule
    , SharedModule
  ]
  , declarations: [PessoaMainComponent, PessoaFiltroComponent, PessoaGridComponent, ModalPessoaComponent]
  , providers:[PessoaService, ModalPessoaService]
})
export class PessoaModule { }
