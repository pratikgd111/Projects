import { VendorserviceService } from './../vendor/vendorservice.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vendor-signup',
  templateUrl: './vendor-signup.component.html',
  styleUrls: ['./vendor-signup.component.css']
})
export class VendorSignupComponent implements OnInit {

  mobile="";
  password="";
  shopName="";
  domain="";
  role="VENDOR";

  domains=[]

  constructor(
    private vendorservice:VendorserviceService,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.vendorservice.getAllDomains().subscribe((response:[])=>{
      this.domains=response
      console.log(this.domains);
  }, (error)=>{
    console.log(error);
  })

  }

  onSubmit() {

    const body = {
      mobile:this.mobile,
      password:this.password,
      role:this.role
    }
    this.vendorservice.vendorSignup(body, this.shopName, this.domain).subscribe((response)=>{
        console.log(response);
        alert("You have signed up successfully. Please login again.....");
        this.router.navigate(['/login'])
        
    }, (error)=>{
      console.log(error);
      alert("something went wrong. Please try again......");
      
    })

  }

}
