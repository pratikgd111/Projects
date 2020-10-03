import { BrandsService } from './../brands.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CategoryService } from '../category.service';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css'],
})
export class ProductAddComponent implements OnInit {
  title = '';
  price = 0;
  description = '';
  category = 0;
  brand = 0;

  product = null;

  products = [];
  categories = [];
  brands = [];

  constructor(
    private router: Router,
    private productService: ProductService,
    private activatedRoute: ActivatedRoute,
    private categoryService: CategoryService,
    private brandService: BrandsService
  ) {}

  ngOnInit(): void {
    this.loadCategories();
    this.loadBrands();

    const id = this.activatedRoute.snapshot.queryParams['id'];
    if (id) {
      this.productService.getProductDetails(id).subscribe((response) => {
        if (response['status'] == 'success') {
          this.products = response['data'];
          if (this.products.length > 0) {
            this.product = this.products[0];
            console.log(this.product);
            this.title = this.product['title'];
            this.description = this.product['description'];
            this.price = this.product['price'];
            this.category = this.product['category']['id'];
            this.brand = this.product['brand']['id'];
          }
        } else {
          console.error(response['error']);
        }
      });
    }
  }

  onUpdate(product) {
    if (this.product) {
      // update product
      this.productService
        .updateProduct(
          this.product['id'],
          this.title,
          this.description,
          this.price,
          this.category,
          this.brand
        )
        .subscribe((response) => {
          if (response['status'] == 'success') {
            alert('product updated successfully');
            this.router.navigate(['/product-list']);
          } else {
            console.error(response['error']);
          }
        });
    } else {
      // add new product
      this.productService
        .insertProduct(
          this.title,
          this.description,
          this.price,
          this.category,
          this.brand
        )
        .subscribe((response) => {
          if (response['status'] == 'success') {
            alert('product added successfully');
            this.router.navigate(['/product-list']);
          } else {
            console.error(response['error']);
          }
        });
    }
  }

  loadCategories() {
    this.categoryService.getCategories().subscribe((response) => {
      if (response['status'] == 'success') {
        this.categories = response['data'];
      } else {
        console.error(response['error']);
      }
    });
  }

  loadBrands() {
    this.brandService.getBrands().subscribe((response) => {
      if (response['status'] == 'success') {
        this.brands = response['data'];
        this.brand = this.brands[0];
        console.log('brand :');
        console.log(this.brand);
      } else {
        console.error(response['error']);
      }
    });
  }

  onUploadImage() {
    this.router.navigate(['/product-upload-image'], {
      queryParams: { id: this.product['id'] },
    });
  }

  onDelete() {}
}
