import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TaikhoanthanhtoanService {

  constructor(private http: HttpClient) { }

  findTaiKhoanGuiVaNhan(value: any) {
    return this.http.post(`${environment.apiUrl}/taikhoanthanhtoan/findTaiKhoanGuiVaNhan`, value);
  }

  confirmTransfer(value: any) {
    return this.http.post(`${environment.apiUrl}/taikhoanthanhtoan/confirmTransfer`, value);
  }

}
