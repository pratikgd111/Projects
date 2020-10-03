import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  url = 'http://localhost:4000/product';

  constructor(private httpClient: HttpClient) {}

  searchProduct(name) {
    console.log(name);
    // add the token in the request header
    const httpOptions = {
      headers: new HttpHeaders({
        token: sessionStorage['token'],
      }),
    };
    const url1 = 'http://localhost:4000/product/search';

    return this.httpClient.get(url1 + '/' + name, httpOptions);
  }

  getProducts() {
    // add the token in the request header
    const httpOptions = {
      headers: new HttpHeaders({
        token: sessionStorage['token'],
      }),
    };

    return this.httpClient.get(this.url, httpOptions);
  }
}
