import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
})
export class ProductListComponent implements OnInit {
  products = [];

  constructor(private router: Router, private productService: ProductService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
    this.productService.getProducts().subscribe((response) => {
      if (response['status'] == 'success') {
        this.products = response['data'];

        console.log(this.products);
      } else {
        console.log(response['error']);
      }
    });
  }

  toggleActiveStatus(product) {
    this.productService.toggleActivateStatus(product).subscribe((response) => {
      if (response['status'] == 'success') {
        this.loadProducts();
      } else {
        console.log(response['error']);
      }
    });
  }

  onEdit(product) {
    this.router.navigate(['/product-add'], {
      queryParams: { id: product['id'] },
    });
  }

  uploadImage(product) {
    this.router.navigate(['/product-upload-image'], {
      queryParams: { id: product['id'] },
    });
  }

  addProduct() {
    this.router.navigate(['/product-add']);
  }

  onDelete(id) {
    console.log(id);
    this.productService.deleteProduct(id).subscribe((response) => {
      if (response['status'] == 'success') {
        alert('product deleted successfully');
        this.loadProducts();
      } else {
        alert('Product Not Deleted');
        console.log(response['error']);
      }
    });
  }
}
