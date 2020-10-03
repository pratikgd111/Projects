import { Router } from '@angular/router';
import { CartService } from './../cart.service';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Alertify } from 'alertify.js';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  items = [];
  // calculate

  totalAmount = 0;
  temp = 0;

  constructor(
    private router: Router,
    private toastr: ToastrService,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    this.loadCartItems();
  }

  // load cart data

  loadCartItems() {
    this.cartService.getCartItems().subscribe((response) => {
      if (response['status'] == 'success') {
        this.items = response['data'];
        for (let index = 0; index < this.items.length; index++) {
          const item = this.items[index];
          this.temp += parseFloat(item['totalAmount']);
          this.totalAmount += this.temp;
          this.temp = 0;
        }
        console.log(this.items);
      } else {
        console.error(response['error']);
      }
    });
  }

  // update quantity

  updateQuantity(quantity, item) {
    const newQuantity = item['quantity'] + quantity;
    if (newQuantity == 0) {
      // this.(item);
    } else {
      if (quantity == 1) {
        this.cartService
          .updateQuantity(item['productId'], item['price'], newQuantity)
          .subscribe((response) => {
            if (response['status'] == 'success') {
              this.toastr.success(
                `${newQuantity} ${item['title']} added to cart`
              );
              this.loadCartItems();
            } else {
              console.error(response['error']);
            }
          });
      } else {
        this.cartService
          .updateQuantity(item['productId'], item['price'], newQuantity)
          .subscribe((response) => {
            if (response['status'] == 'success') {
              this.toastr.success(`1 ${item['title']} remove from cart`);
              this.loadCartItems();
            } else {
              console.error(response['error']);
            }
          });
      }
    }
  }

  // delete cart item
  onDelete(item) {
    this.cartService.deleteCartItem(item['id']).subscribe((response) => {
      if (response['status'] == 'success') {
        this.toastr.success('deleted cart item');
        this.loadCartItems();
      }
    });
  }
}
