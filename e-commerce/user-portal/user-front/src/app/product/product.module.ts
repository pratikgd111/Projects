import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductRoutingModule } from './product-routing.module';
import { GalleryComponent } from './gallery/gallery.component';
import { CartComponent } from './cart/cart.component';
import { ProductService } from './product.service';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [GalleryComponent, CartComponent],
  imports: [CommonModule, ProductRoutingModule, FormsModule],
  providers: [ProductService],
})
export class ProductModule {}
