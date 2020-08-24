import { Observable, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { map, catchError, timeoutWith } from 'rxjs/operators';
import { HandleErrorService } from './handle-error.service';
import { HttpStatusCode } from '../globlas/enums';
import { Router } from '@angular/router';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends ApiService {

    onLogin(value: any){
        return this.httpPost('/Authenticate/login', value);
    }
}
