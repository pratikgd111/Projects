import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class BrandsService {
  url = 'http://localhost:3000/brand';

  constructor(private httpClient: HttpClient) {}

  getBrands() {
    const httpOptions = {
      headers: new HttpHeaders({
        token: sessionStorage['token'],
      }),
    };
    return this.httpClient.get(this.url, httpOptions);
  }
}
