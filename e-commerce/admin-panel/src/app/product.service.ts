import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root',
})
export class ProductService {
  url = 'http://localhost:3000/product';

  constructor(private httpClient: HttpClient) {}

  getProducts() {
    // add the token in the request header
    const httpOptions = {
      headers: new HttpHeaders({
        token: sessionStorage['token'],
      }),
    };

    return this.httpClient.get(this.url, httpOptions);
  }

  updateProduct(
    id: number,
    title: string,
    description: string,
    price: number,
    category: number,
    brand: number
  ) {
    // add the token in the request header
    const httpOptions = {
      headers: new HttpHeaders({
        token: sessionStorage['token'],
      }),
    };

    const body = {
      title: title,
      description: description,
      price: price,
      category: category,
      brand: brand,
    };

    return this.httpClient.put(this.url + '/' + id, body, httpOptions);
  }

  insertProduct(
    title: string,
    description: string,
    price: number,
    category: number,
    brand: number
  ) {
    // add the token in the request header
    const httpOptions = {
      headers: new HttpHeaders({
        token: sessionStorage['token'],
      }),
    };

    const body = {
      title: title,
      description: description,
      price: price,
      category: category,
      brand: brand,
    };

    return this.httpClient.post(this.url + '/create', body, httpOptions);
  }

  getProductDetails(id) {
    // add the token in the request header
    const httpOptions = {
      headers: new HttpHeaders({
        token: sessionStorage['token'],
      }),
    };

    return this.httpClient.get(this.url + '/details/' + id, httpOptions);
  }

  toggleActivateStatus(product) {
    // add the token in the request header
    const httpOptions = {
      headers: new HttpHeaders({
        token: sessionStorage['token'],
      }),
    };

    const body = {};
    return this.httpClient.put(
      this.url +
        `/update-state/${product['id']}/${product['isActive'] == 0 ? 1 : 0}`,
      body,
      httpOptions
    );
  }

  deleteProduct(id) {
    console.log(`inside p service ${id}`);
    // add the token in the request header
    const httpOptions = {
      headers: new HttpHeaders({
        token: sessionStorage['token'],
      }),
    };
    return this.httpClient.delete(this.url + `/delete/${id}`, httpOptions);
  }

  uploadImage(id, file) {
    // add the token in the request header
    const httpOptions = {
      headers: new HttpHeaders({
        token: sessionStorage['token'],
      }),
    };

    const body = new FormData();
    body.append('image', file);
    // console.log(body);

    return this.httpClient.post(
      this.url + `/upload-image/${id}`,
      body,
      httpOptions
    );
  }
  // upload image of new product
  // uploadNewImage(file) {
  //   // add the token in the request header
  //   const httpOptions = {
  //     headers: new HttpHeaders({
  //       token: sessionStorage['token'],
  //     }),
  //   };

  //   const body = new FormData();
  //   body.append('image', file);
  //   // console.log(body);
  //   console.log(file);
  //   return this.httpClient.post(this.url + `/upload-image`, body, httpOptions);
  // }

  // end
}
