import { Output } from '@angular/core';
import { Component, EventEmitter, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from '../product/category.service';
import { ProductService } from '../product/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(
    private categoryService: CategoryService,
    private productService: ProductService, // private modalService: NgbModal
    private router: Router
  ) {}

  ngOnInit(): void {}

  onLogout() {
    sessionStorage.removeItem('firstName');
    sessionStorage.removeItem('lastName');
    sessionStorage.removeItem('token');

    // force user to go to login again
    this.router.navigate(['/auth/login']);
  }
}
