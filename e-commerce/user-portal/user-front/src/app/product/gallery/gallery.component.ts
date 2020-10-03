import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from '../cart.service';
import { ProductService } from '../product.service';
import { CategoryService } from '../category.service';
// import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CartComponent } from '../cart/cart.component';

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css'],
})
export class GalleryComponent implements OnInit {
  productTitle = '';

  products = [];
  quantity = 1;
  allProducts = [];

  categoryId = -1;
  categories = [];

  constructor(
    private categoryService: CategoryService,
    private toastr: ToastrService,
    private cartService: CartService,
    private router: Router,
    private productService: ProductService // private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.loadProducts();
    this.loadCategories();
  }

  searchProducts() {
    console.log(this.productTitle);

    this.productService
      .searchProduct(this.productTitle)
      .subscribe((response) => {
        if (response['status'] == 'success') {
          this.products = response['data'];
          console.log(this.products);
          this.productService.getProducts();
        } else {
          console.error(response['error']);
        }
      });
  }

  filterProducts(event) {
    const categoryId = event.target.value;
    console.log(categoryId);
    this.products = [];
    if (categoryId == -1) {
      this.products = this.allProducts;
    } else {
      this.products = this.allProducts.filter((product) => {
        return product.category['id'] == categoryId;
      });
    }
  }

  loadCategories() {
    this.categoryService.getCategories().subscribe((response) => {
      if (response['status'] == 'success') {
        this.categories = response['data'];
        // this.categories.push({ id: -1, title: 'All Categories' });
        console.log(this.categories);
      }
    });
  }

  // loadCart() {
  //   this.modalService.open(CartComponent, { size: 'lg' });
  // }

  loadProducts() {
    this.productService.getProducts().subscribe((response) => {
      console.log(response);
      if (response['status'] == 'success') {
        this.allProducts = response['data'];
        this.products = this.allProducts;
        console.log(this.allProducts);
      }
    });
  }

  addToCart(product) {
    this.cartService
      .addCartItem(product['id'], product['price'], 1)
      .subscribe((response) => {
        if (response['status'] == 'success') {
          this.toastr.success('added your product to cart');
        }
      });
  }
}
