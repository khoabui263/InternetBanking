import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})
export class TaiKhoanDangNhapService {

    constructor(private http: HttpClient) { }

    findAllDetailsTaiKhoanDangNhap(value: any) {
        return this.http.post(`${environment.apiUrl}/taikhoanthanhtoan/findalltaikhoanthanhtoan`, value);
    }

    sendEmailChangePassWord(value: any) {
        return this.http.post(`${environment.apiUrl}/taikhoandangnhap/sendEmailChangePassWord`, value);
    }


    confirmChangePassWord(value: any) {
        return this.http.post(`${environment.apiUrl}/taikhoandangnhap/confirmChangePassWord`, value);
    }

}