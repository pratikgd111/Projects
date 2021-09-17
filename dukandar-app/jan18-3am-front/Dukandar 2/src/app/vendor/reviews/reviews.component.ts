import { VendorserviceService } from './../vendorservice.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrls: ['./reviews.component.css']
})
export class ReviewsComponent implements OnInit {


  shopreviewinfo: any[] = []

  constructor(private vendorservice: VendorserviceService) { }

  ngOnInit(): void {
    this.vendorservice.getAllShopReviews().subscribe((response: []) => {

      this.shopreviewinfo = response

      console.log(response);
      console.log(this.shopreviewinfo);


    })
  }

}
