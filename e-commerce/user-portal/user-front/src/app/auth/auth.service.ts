import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
} from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService implements CanActivate {
  url = 'http://localhost:4000/user';
  constructor(
    private httpClient: HttpClient,
    private router: Router // private canActivate: CanActivate
  ) {}

  login(email, password) {
    const body = {
      email: email,
      password: password,
    };
    return this.httpClient.post(this.url + '/signin', body);
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (sessionStorage['token']) {
      return true;
    }
    // force user to login
    this.router.navigate(['/auth/login']);
    return false;
  }
}
