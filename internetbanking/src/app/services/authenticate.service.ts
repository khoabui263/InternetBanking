import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  constructor(private http: HttpClient) { }

  login(login: any) {
    return this.http.post(`${environment.apiUrl}/authenticate/login`, login);
  }

  test() {
    return this.http.get(`${environment.apiUrl}/test`);
  }

  sendEmailCheckAccountExisted(value: any) {
    return this.http.post(`${environment.apiUrl}/authenticate/sendEmailCheckAccountExisted`, value);
  }

  forgetPassword(value: any) {
    return this.http.post(`${environment.apiUrl}/authenticate/forgetPassword`, value);
  }
}
