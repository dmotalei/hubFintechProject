import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContaMainComponent } from './conta-main/conta-main.component';
import { ContaFiltroComponent } from './conta-filtro/conta-filtro.component';
import { ContaGridComponent } from './conta-grid/conta-grid.component';
import { ModalContaComponent } from './modal-conta/modal-conta.component';
import { FormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/shared/shared.module';
import { ContaService } from './conta.service';
import { ModalContaService } from './modal-conta.service';
import { ContaRoutingModule } from './conta-routing.module';

@NgModule({
  imports: [
    CommonModule
    , SharedModule
    , FormsModule
    , ContaRoutingModule
  ],
  declarations: [ContaMainComponent, ContaFiltroComponent, ContaGridComponent, ModalContaComponent],
  providers: [ContaService, ModalContaService]
})
export class ContaModule { }
