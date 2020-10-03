import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.css'],
})
export class UploadImageComponent implements OnInit {
  products = [];
  product = {};
  selectedFile = null;
  constructor(
    private activatedRoute: ActivatedRoute,
    private productService: ProductService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // get the product for which image to be uploaded
    const id = this.activatedRoute.snapshot.queryParams['id'];
    if (id) {
      this.productService.getProductDetails(id).subscribe((response) => {
        if (response['status'] == 'success') {
          this.products = response['data'];
          if (this.products.length > 0) {
            this.product = this.products[0];
            console.log(this.product);
          }
        } else {
          console.error(response['error']);
        }
      });
    }
  }

  onImageSelect(event) {
    this.selectedFile = event.target.files[0];
    console.log(this.selectedFile);
  }

  onUploadImage() {
    if (this.product['id']) {
      this.productService
        .uploadImage(this.product['id'], this.selectedFile)
        .subscribe((response) => {
          if (response['status'] == 'success') {
            this.router.navigate(['/product-list']);
          } else {
            console.error(response['error']);
          }
        });
    } else {
      // this.productService
      //   .uploadNewImage(this.selectedFile)
      //   .subscribe((response) => {
      //     if (response['status'] == 'success') {
      //       // this.router.navigate(['/product-add']);
      //       console.log(response['data']);
      //     } else {
      //       console.error(response['error']);
      //     }
      //   });
    }
  }
}
