import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { WebStorageSerivce } from './webstorage.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NhacnoService {

  constructor(
    private http: HttpClient,
    private webStorageSerivce: WebStorageSerivce
  ) { }

  saveNhacNo(value: any) {
    return this.http.post(`${environment.apiUrl}/nhacno/saveNhacNo`, value);
  }

  getDanhSachNo(value: any) {
    return this.http.post(`${environment.apiUrl}/nhacno/getDanhSachNo`, value);
  }

  updateLoaiNhacNo(value: { id: number; maloainhacno: number; }) {
    return this.http.put(`${environment.apiUrl}/nhacno/updateLoaiNhacNo`, value);
  }

  payDebt(value: {}) {
    return this.http.post(`${environment.apiUrl}/nhacno/payDebt`, value);
  }
}
