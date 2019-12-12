import {Injectable} from "@angular/core";
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from "@angular/common/http";
import {Observable} from "rxjs";
import {Router} from "@angular/router";
import {tap} from "rxjs/operators";

@Injectable()
export class Interceptor implements HttpInterceptor {
  private readonly urls: string[] = ['/', '/registration', '/firstPagePoll', '/poll'];

  constructor(private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const cloneReq = req.clone({
      headers: req.headers.set('Authorization', 'Bearer_' + localStorage.getItem('token'))
    });
    if (this.isExceptionUrl()) {
      return next.handle(req);
    } else {
      return next.handle(cloneReq).pipe(
        tap(event => {
          if (event instanceof HttpResponse)
            console.log('Server response');
        }, err => {
          if (err instanceof HttpErrorResponse) {
            if (err.status == 401) {
              console.log('Unauthorized');
              localStorage.setItem('err','Unauthorized');
              this.router.navigate(['/']);
            }

          }
        })
      );
    }
  }

  private isExceptionUrl(): boolean {
    if (this.router.url === '/')
      return true;
    for (let i = 1; i < this.urls.length; i++) {
      if (this.router.url.includes(this.urls[i]))
        return true;
    }
    return false;
  }
}
