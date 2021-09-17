import { UserSignupComponent } from './user-signup/user-signup.component';
import { VendorSignupComponent } from './vendor-signup/vendor-signup.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
 { path:'' ,redirectTo: '/login' , pathMatch:'full'},
  { path:'login' , component:LoginComponent},
  { path:'register' , component:RegisterComponent},
  { path:'vendor-signup' , component:VendorSignupComponent},
  { path:'user-signup' , component:UserSignupComponent},
  { path: 'vendor', loadChildren: () => import('./vendor/vendor.module').then(m => m.VendorModule)},
  { path: 'client', loadChildren: () => import('./client/client.module').then(m => m.ClientModule)}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
