import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { MenuComponent } from './menu/menu.component';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { PessoaModule } from './pessoa/pessoa.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RequestInterceptorService } from './core/request-interceptor.service';
import { ContaModule } from './conta/conta.module';
import { TransacaoModule } from './transacao/transacao.module';



@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule
    , HttpClientModule 
    , AppRoutingModule
    , PessoaModule 
    , ContaModule
    , TransacaoModule
    
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: RequestInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
