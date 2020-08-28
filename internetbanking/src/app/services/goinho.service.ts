import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GoinhoService {

  constructor(private http: HttpClient) { }

  saveReminder(value: any) {
    return this.http.post(`${environment.apiUrl}/goinho/saveReminder`, value);
  }

  findByMataikhoancannhoAndTrangthai(value: any) {
    return this.http.post(`${environment.apiUrl}/goinho/findByMataikhoancannhoAndTrangthai`, value);
  }

  findGoiNhoExisted(value: any) {
    return this.http.post(`${environment.apiUrl}/goinho/findGoiNhoExisted`, value);
  }

  activateGoiNho(value: any) {
    return this.http.put(`${environment.apiUrl}/goinho/activateGoiNho`, value);
  }

  deleteReminder(value: any) {
    return this.http.put(`${environment.apiUrl}/goinho/deleteReminder`, value);
  }
}
