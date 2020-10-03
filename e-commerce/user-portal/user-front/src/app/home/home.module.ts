import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CategoryService } from '../product/category.service';
import { ProductService } from '../product/product.service';

@NgModule({
  declarations: [],
  imports: [CommonModule, FormsModule],
  providers: [ProductService, CategoryService],
})
export class HomeModule {}
