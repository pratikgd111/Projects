import { VendorserviceService } from './../vendorservice.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-enquiry',
  templateUrl: './enquiry.component.html',
  styleUrls: ['./enquiry.component.css']
})
export class EnquiryComponent implements OnInit {

  enquiries:any[]=[]

  constructor(
    private venderservice:VendorserviceService

  ) { }

  ngOnInit(): void {
    this.venderservice.getAllEnquiries().subscribe((response:[])=>{
      this.enquiries = response

      console.log(this.enquiries);

    })
  }

}
