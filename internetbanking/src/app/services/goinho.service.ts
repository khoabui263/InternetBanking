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
}
