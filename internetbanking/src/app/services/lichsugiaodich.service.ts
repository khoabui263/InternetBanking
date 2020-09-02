import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LichsugiaodichService {

  constructor(private http: HttpClient) { }

  getLichSuGiaoDich(value: any) {
    return this.http.post(`${environment.apiUrl}/lichsugiaodich/getLichSuGiaoDich`, value);
  }

  getChuyenTien(value: any) {
    return this.http.post(`${environment.apiUrl}/lichsugiaodich/getChuyenTien`, value);
  }

  getNhanTien(value: any) {
    return this.http.post(`${environment.apiUrl}/lichsugiaodich/getNhanTien`, value);
  }

  getTraNo(value: any) {
    return this.http.post(`${environment.apiUrl}/lichsugiaodich/getTraNo`, value);
  }

  getNguoiKhacTraNo(value: any) {
    return this.http.post(`${environment.apiUrl}/lichsugiaodich/getNguoiKhacTraNo`, value);
  }
}