import { Injectable } from '@angular/core';
import {
  HttpEvent, 
  HttpInterceptor, 
  HttpHandler, 
  HttpRequest,
  HttpResponse
} from '@angular/common/http'
import {tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RequestInterceptorService implements HttpInterceptor {
  
    intercept(req: HttpRequest<any>, next: HttpHandler) {
       
      return next.handle(req) 
        .pipe(
          tap(event => {
            if (event instanceof HttpResponse) {
             
            }
          }, error => {
            
          // http response status code
              console.log("----response----");
              console.error(error.message);
              console.log("--- end of response---");

              if(error.status == 0){
                error.error = "Servidor não encontrado!"
              }
          })
      )
    }
  
    
  }
  