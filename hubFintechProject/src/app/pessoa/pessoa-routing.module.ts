import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PessoaMainComponent } from './pessoa-main/pessoa-main.component';

const routes: Routes = [
  {
    path: 'pessoa'
    , component: PessoaMainComponent
  }
];

@NgModule({ 
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PessoaRoutingModule { }
