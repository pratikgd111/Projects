import { VendorserviceService } from './../vendorservice.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-showposts',
  templateUrl: './showposts.component.html',
  styleUrls: ['./showposts.component.css']
})
export class ShowpostsComponent implements OnInit {

  // offerimage='https://magicpin-image-assets.s3-ap-southeast-1.amazonaws.com/Remarketing-Pics/Rmkt/MO_NB+_F%26B.jpg'
  // Offername='Non-veg offer'
  // discount='50%'
  // Offerlastto='23-jan-2021'
  // like=500
  // dislike=100


  posts=[];

  constructor(
    private vendorService:VendorserviceService
  ) { }

  ngOnInit(): void {
      this.vendorService.getAllPostsOfVendor().subscribe(
        (response:[]) => {
          console.log(response);
          this.posts=response
        },
        (error) => {
          alert("Something Went Wrong")
          console.log(error);
        }
      );
  }


}
