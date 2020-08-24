import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WebStorageSerivce } from './webstorage.service';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { catchError, mapTo, tap, delay, flatMap, switchMap } from 'rxjs/operators';
import { MatDialog } from '@angular/material';
import { DialogErrorsComponent } from '../share/dialog-errors/dialog-errors.component';
import { environment } from 'src/environments/environment';
import { WebKeyStorage } from '../globlas/web-key-storage';

@Injectable()
export class InterceptorService implements HttpInterceptor {

  constructor(
    private router: Router,
    private http: HttpClient,
    private webStorageSerivce: WebStorageSerivce,
    public matDialog: MatDialog,
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): any {
    const tokenInfo = this.webStorageSerivce.getLocalStorage('token-info');
    if (tokenInfo) {
      req = req.clone({
        setHeaders: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + tokenInfo.accessToken
        }
      });
    }
    return next.handle(req).pipe(catchError((error: any) => {
      if (error.status === 401) {
        // if (req.url.includes('api/authenticate/login')) {
        //   this.showErorrDialog('Sai thông tin đăng nhập, vui lòng đăng nhập lại');
        //   return throwError(error);
        // }
        const oldTokens = {
          accessToken: tokenInfo.accessToken,
          refreshToken: tokenInfo.refreshToken,
          userName: tokenInfo.userName
        };
        console.log('Interceptor: 401');
        return this.http.post<any>(`${environment.apiUrl}/authenticate/refeshToken`, oldTokens).pipe(
          tap((newToken: any) => {
            console.log(newToken);
            this.webStorageSerivce.setLocalStorage(
              WebKeyStorage.token_info, { accessToken: newToken.accessToken,
                                          refeshToken: newToken.refeshToken,
                                          userName: newToken.userName }
            );
          }),

          flatMap((newToken: any) => {
            req = req.clone({
              setHeaders: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + newToken.accessToken
              }
            });
            return next.handle(req);
          }),

          catchError((err) => {
            this.webStorageSerivce.clearLocalStorage();
            this.router.navigateByUrl('/');
            return throwError(err);
          })

        )
      }
      return throwError(error);
    }));
  }

  showErorrDialog(text: any) {
    this.matDialog.open(DialogErrorsComponent, {
      disableClose: true,
      height: '300px',
      width: '300px',
      data: { message: text }
    });
  }

}
