import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { OrderListComponent } from './order-list/order-list.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserListComponent } from './user-list/user-list.component';
import { ProductListComponent } from './product-list/product-list.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminService } from './admin.service';
import { ProductAddComponent } from './product-add/product-add.component';
import { UploadImageComponent } from './upload-image/upload-image.component';

const routes: Routes = [
  {
    path: 'product-list',
    component: ProductListComponent,
    canActivate: [AdminService],
  },
  {
    path: 'user-list',
    component: UserListComponent,
    canActivate: [AdminService],
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [AdminService],
  },
  {
    path: 'order-list',
    component: OrderListComponent,
    canActivate: [AdminService],
  },

  {
    path: 'product-add',
    component: ProductAddComponent,
    canActivate: [AdminService],
  },
  {
    path: 'product-upload-image',
    component: UploadImageComponent,
    canActivate: [AdminService],
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'signup',
    component: SignupComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
