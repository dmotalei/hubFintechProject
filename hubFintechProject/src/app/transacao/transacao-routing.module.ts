import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TransacaoMainComponent } from './transacao-main/transacao-main.component';

const routes: Routes = [
  {
    path: 'transacao'
    , component: TransacaoMainComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransacaoRoutingModule { }
