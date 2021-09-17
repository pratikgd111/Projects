import { AuthGuardService } from './authguard.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule}  from 'ngx-toastr';
import { FormsModule} from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import { VendorSignupComponent } from './vendor-signup/vendor-signup.component';
import { UserSignupComponent } from './user-signup/user-signup.component'
import { JwtModule } from '@auth0/angular-jwt';



export function tokenGetter() {
  return localStorage.getItem("token");
}


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    VendorSignupComponent,
    UserSignupComponent
  ],
  imports: [
    JwtModule.forRoot(
      {
        config: {
          tokenGetter: tokenGetter,
          allowedDomains: ["localhost:4200"]
        },
      }
    ),
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    FormsModule
    
  ],
  providers: [
    AuthGuardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
