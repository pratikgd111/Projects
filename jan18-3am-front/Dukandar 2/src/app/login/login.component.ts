import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
// import { TokenStorageService } from '../token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  mobile = '';
  password = '';

  role = '';
  // isLoggedIn = false;
  // isLoginFailed = false;
  // errorMessage = '';
  // roles: string[] = [];

  // private tokenStorage: TokenStorageService
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  // onSubmit(): void {
  //   this.authService.login(this.mobile, this.password).subscribe(
  //     (data) => {
  //       // this.tokenStorage.saveToken(data.accessToken);
  //       // this.tokenStorage.saveUser(data);

  //       // this.isLoginFailed = false;
  //       // this.isLoggedIn = true;
  //       // this.roles = this.tokenStorage.getUser().roles;
  //       // this.reloadPage();

  //       alert('You have signed in successfully');
  //       localStorage.setItem('token', data['token']);
  //       console.log(data);

  //       this.authService.getAccount().subscribe(
  //         (response) => {

  //           console.log(response);
  //           this.role = response['role']
  //           console.log(this.role == "VENDOR");

  //           if (this.role == "VENDOR") {
  //             this.router.navigate(['/vendor/vendorregister']);
  //           }

  //         },
  //         (error) => {
  //           console.log(error);
  //         }
  //       );

  //     },
  //     (err) => {
  //       alert('Something went wrong. Please try again.....')
  //       // this.errorMessage = err.error.message;
  //       // this.isLoginFailed = true;
  //       console.log(err);
  //     }
  //   );
  // }

  role1 = 'USER';
  role2 = 'VENDOR';

  onSubmit(): void {
    this.authService.login(this.mobile, this.password).subscribe(
      (data) => {
        localStorage.setItem('token', data['token']);
        console.log(data);
        console.log(data['role']);
        if (data['role'] == this.role1) {
          // if it is user navigate it to home page
          this.router.navigate(['/client/home']);
        } else if (data['role'] == this.role2)
          this.router.navigate(['/vendor/vendorregister']);
      },
      (err) => {
        console.log(err);
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }
}
