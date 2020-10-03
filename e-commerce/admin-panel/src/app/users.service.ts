import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  url = 'http://localhost:3000/user';
  constructor(private httpClent: HttpClient) {}

  getUsers() {
    const httpOptions = {
      headers: new HttpHeaders({
        token: sessionStorage['token'],
      }),
    };

    return this.httpClent.get(this.url, httpOptions);
  }

  toggleUserActiveStatus(user) {
    console.log(user['active']);
    const httpOptions = {
      headers: new HttpHeaders({
        token: sessionStorage['token'],
      }),
    };
    // suspend user if active or vice-versa
    const body = {
      status: user['active'] == 1 ? 0 : 1,
    };
    return this.httpClent.put(
      this.url + '/toggle-active/' + user['id'],
      body,
      httpOptions
    );
  }
}
