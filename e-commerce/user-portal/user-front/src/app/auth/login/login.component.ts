import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  email = '';
  password = '';
  userInfo = {};
  constructor(
    private toastr: ToastrService,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {}

  onLogin() {
    // client side form validation
    if (this.email.length == 0 || this.email.startsWith('@')) {
      this.toastr.error('please enter a email');
    } else if (this.password.length == 0) {
      this.toastr.error('please enter a password');
    } else {
      this.authService
        .login(this.email, this.password)
        .subscribe((response) => {
          if (response['status'] == 'success') {
            this.userInfo = response['data'];
            // console.log(this.userInfo);
            // store info to session storage
            sessionStorage['firstName'] = this.userInfo['firstName'];
            sessionStorage['lastName'] = this.userInfo['lasstName'];
            sessionStorage['token'] = this.userInfo['token'];

            this.toastr.success(
              `Welcome ${this.userInfo['firstName']} to MyStore`
            );
            this.router.navigate(['/home/product/gallery']);
          } else {
            // console.error(response['error']);
            this.toastr.error(response['error']);
          }
        });
    }
  }
}
