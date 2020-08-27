import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { WebStorageSerivce } from './webstorage.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NganhangService {

  constructor(
    private http: HttpClient,
    private webStorageSerivce: WebStorageSerivce
  ) { }

  getDanhSachNganHang() {
    return this.http.get(`${environment.apiUrl}/nganhang`);
  }
}
