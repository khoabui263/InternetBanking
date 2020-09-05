import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { of, Observable } from 'rxjs';
import { WebStorageSerivce } from './webstorage.service';
import * as jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(
    private http: HttpClient,
    private webStorageSerivce: WebStorageSerivce) { }

  searchTaiKhoanThanhToan(term: string): any {
    if (!term.trim()) {
      return of([]);
    }
    const value = {
      mataikhoanthanhtoan: Number(term)
    };

    return this.http.post(`${environment.apiUrl}/search/searchTaiKhoanThanhToan`, value);
  }

  searchGoiNho(term: string, selectedBank: number): any {
    if (!term.trim()) {
      return of([]);
    }

    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    const value = {
      mataikhoancannho: Number.parseInt(decodeAT.mataikhoan, 10),
      chuoimanguoigoinho: term,
      manganhang: Number(selectedBank)
    };

    console.log('Goi api: ' + term);

    return this.http.post(`${environment.apiUrl}/search/searchGoiNho`, value);
  }

  searchTaiKhoanNhanVien(term: string): any {
    if (!term.trim()) {
      return of([]);
    }
    const value = {
      email: term
    };
    return this.http.post(`${environment.apiUrl}/search/searchTaiKhoanNhanVien`, value);
  }

}
