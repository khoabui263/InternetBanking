import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { of, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  searchTaiKhoanThanhToan(term: string): any {
    if (!term.trim()) {
      return of([]);
    }
    const value = {
      mataikhoanthanhtoan: Number(term)
    };

    return this.http.post(`${environment.apiUrl}/search/searchTaiKhoanThanhToan`, value);
  }

}
