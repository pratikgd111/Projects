import { Router } from '@angular/router';
import { VendorserviceService } from './../vendorservice.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-shop-photo',
  templateUrl: './shop-photo.component.html',
  styleUrls: ['./shop-photo.component.css']
})
export class ShopPhotoComponent implements OnInit {

  selectedFile: File;

  photos = [];

  constructor(private vendorservice:VendorserviceService,
    private router:Router
    ) { }

  ngOnInit(): void {
    this.vendorservice.getAllShopPhotos().subscribe(
      (response:[]) => {
        this.photos = response
        console.log(this.photos);
      },
      (error) => {
        console.log(error);
      }
    );
  }

 


  onFileChanged(event) {
    this.selectedFile = event.target.files[0];
  }

  onUpload() {
    this.vendorservice
      .uploadShopPhoto(this.selectedFile).subscribe(
        (response:[]) => {
          alert("Saved Successfully")
          this.photos = response
          console.log(this.photos);
        },
        (error) => {
          alert("Something Went Wrong")
          console.log(error);
        }
      );

  }

  onNext(){
      this.router.navigate(['/vendor/showprofile'])
  }

}
