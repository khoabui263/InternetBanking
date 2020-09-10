import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LiennganhangService {

  constructor(
    private http: HttpClient,
  ) { }

  localFindAccountRSA(value: any) {
    return this.http.post(`${environment.apiUrl}/liennganhang/localFindAccountRSA`, value);
  }

  confirmTransferLocalToRSA(value: any) {
    return this.http.post(`${environment.apiUrl}/liennganhang/confirmTransferLocalToRSA`, value);
  }
}
