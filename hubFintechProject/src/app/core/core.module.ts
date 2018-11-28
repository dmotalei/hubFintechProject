import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CoreRoutingModule } from './core-routing.module';
import { RequestInterceptorService } from 'src/app/core/request-interceptor.service';

@NgModule({
  imports: [
    CommonModule,
    CoreRoutingModule
  ],
  declarations: [], 
  providers: [RequestInterceptorService]
})
export class CoreModule { } 
