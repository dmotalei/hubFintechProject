import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContaMainComponent } from 'src/app/conta/conta-main/conta-main.component';

const routes: Routes = [
  {
    path: 'conta'
    , component: ContaMainComponent
  }
];

@NgModule({ 
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContaRoutingModule { }
