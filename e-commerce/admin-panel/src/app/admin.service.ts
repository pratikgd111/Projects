import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AdminService implements CanActivate {
  url = 'http://localhost:3000/admin';

  constructor(private router: Router, private httpClient: HttpClient) {}

  login(email: string, password: string) {
    const body = {
      email: email,
      password: password,
    };

    return this.httpClient.post(this.url + '/signin', body);
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    // when user is alredy login
    if (sessionStorage['token']) {
      return true;
    }
    this.router.navigate(['/login']);
    // when user is not login
    return false;
  }
}
