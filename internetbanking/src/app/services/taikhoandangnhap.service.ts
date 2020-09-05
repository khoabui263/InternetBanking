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

    sendEmailChuyenTien(value: any) {
        return this.http.post(`${environment.apiUrl}/taikhoandangnhap/sendEmailChuyenTien`, value);
    }

    sendEmailConfirmInfo(value: any) {
        return this.http.post(`${environment.apiUrl}/taikhoandangnhap/sendEmailConfirmInfo`, value);
    }

    confirmInfo(value: any) {
        return this.http.post(`${environment.apiUrl}/taikhoandangnhap/confirmInfo`, value);
    }

    createEmployee(value: any) {
        return this.http.post(`${environment.apiUrl}/taikhoandangnhap/createEmployee`, value);
    }

    findAllEmployee() {
        return this.http.get(`${environment.apiUrl}/taikhoandangnhap/findAllEmployee`);
    }

    updateEmployee(value: any) {
        return this.http.post(`${environment.apiUrl}/taikhoandangnhap/updateEmployee`, value);
    }

    deleteEmployee(value: { mataikhoan: any; }) {
        return this.http.post(`${environment.apiUrl}/taikhoandangnhap/deleteEmployee`, value);
    }
}