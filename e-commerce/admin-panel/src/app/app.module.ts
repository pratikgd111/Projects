import { CategoryService } from './category.service';
import { BrandsService } from './brands.service';
import { AdminService } from './admin.service';
import { OrderService } from './order.service';
import { ProductService } from './product.service';
import { HttpClientModule } from '@angular/common/http';
import { UsersService } from './users.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductListComponent } from './product-list/product-list.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserListComponent } from './user-list/user-list.component';
import { OrderListComponent } from './order-list/order-list.component';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ProductAddComponent } from './product-add/product-add.component';
import { UploadImageComponent } from './upload-image/upload-image.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    HomeComponent,
    DashboardComponent,
    UserListComponent,
    OrderListComponent,
    LoginComponent,
    SignupComponent,
    ProductAddComponent,
    UploadImageComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [
    UsersService,
    ProductService,
    OrderService,
    AdminService,
    BrandsService,
    CategoryService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
