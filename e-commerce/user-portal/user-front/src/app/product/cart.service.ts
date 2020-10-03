import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  url = 'http://localhost:4000/cart';

  constructor(private httpClient: HttpClient) {}

  getCartItems() {
    // add the token in the request header
    const httpOptions = {
      headers: new HttpHeaders({
        token: sessionStorage['token'],
      }),
    };

    return this.httpClient.get(this.url + '/user', httpOptions);
  }

  // add cart item to db
  addCartItem(productId, price, quantity) {
    // console.log('inside cart service');

    const body = {
      productId: productId,
      price: price,
      quantity: quantity,
    };
    // console.log(body);

    // add the token in the request header
    const httpOptions = {
      headers: new HttpHeaders({
        token: sessionStorage['token'],
      }),
    };

    return this.httpClient.post(this.url + '/user', body, httpOptions);
  }

  // update quantity

  // add cart item to db
  updateQuantity(productId, price, quantity) {
    console.log('inside put quantity cart service');

    const body = {
      productId: productId,
      price: price,
      quantity: quantity,
    };
    // console.log(body);

    // add the token in the request header
    const httpOptions = {
      headers: new HttpHeaders({
        token: sessionStorage['token'],
      }),
    };

    return this.httpClient.put(this.url + '/user', body, httpOptions);
  }

  // delete item from cart
  deleteCartItem(id) {
    // add the token in the request header
    const httpOptions = {
      headers: new HttpHeaders({
        token: sessionStorage['token'],
      }),
    };

    return this.httpClient.delete(this.url + '/' + id, httpOptions);
  }
}
