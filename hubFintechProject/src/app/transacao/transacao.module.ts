import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TransacaoRoutingModule } from './transacao-routing.module';
import { TransacaoMainComponent } from './transacao-main/transacao-main.component';
import { TransacaoFiltroComponent } from './transacao-filtro/transacao-filtro.component';
import { TransacaoGridComponent } from './transacao-grid/transacao-grid.component';
import { ModalTransacaoComponent } from './modal-transacao/modal-transacao.component';
import { TransacaoService } from './transacao.service';
import { ModalTransacaoService } from './modal-transacao.service';
import { FormsModule } from '@angular/forms';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    TransacaoRoutingModule
  ],
  declarations: [TransacaoMainComponent, TransacaoFiltroComponent, TransacaoGridComponent, ModalTransacaoComponent],
  providers:[TransacaoService, ModalTransacaoService]

})
export class TransacaoModule { }
