import { VendorserviceService } from './../vendorservice.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-addpost',
  templateUrl: './addpost.component.html',
  styleUrls: ['./addpost.component.css']
})
export class AddpostComponent implements OnInit {

photo="";
offername="";
description='';
offerupto='';

selectedFile: File
insertedPostId="";

  constructor(
    private vendorService:VendorserviceService
  ) { }

  ngOnInit(): void {
  }


  onSubmit(){
    this.vendorService.saveVendorPost(this.offername, this.description, this.offerupto, this.insertedPostId)
    .subscribe((response)=>{
        console.log(response);
        
    })
  }

  onFileChanged(event) {
    this.selectedFile = event.target.files[0]
  }

  onUpload() {
    this.vendorService.uploadVendorPostImage(this.selectedFile)
    .subscribe((response)=>{
      console.log('response', response);
      this.photo= response['photo']

      this.insertedPostId = response['postId'];
    })
  }

}
