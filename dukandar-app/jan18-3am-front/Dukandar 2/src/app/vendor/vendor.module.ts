import { JwtModule } from '@auth0/angular-jwt';
import { EnquiryComponent } from './enquiry/enquiry.component';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VendorRoutingModule } from './vendor-routing.module';
import { VendorregisterComponent } from './vendorregister/vendorregister.component';
import { ShowprofileComponent } from './showprofile/showprofile.component';
import { ShowpostsComponent } from './showposts/showposts.component';
import { AddpostComponent } from './addpost/addpost.component';
import { ReviewsComponent } from './reviews/reviews.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { PersonalinfoComponent } from './personalinfo/personalinfo.component';
import { ShopPhotoComponent } from './shop-photo/shop-photo.component';




@NgModule({
  declarations: [VendorregisterComponent, EnquiryComponent,  ShowprofileComponent, ShowpostsComponent, AddpostComponent, ReviewsComponent, PersonalinfoComponent, ShopPhotoComponent],
  imports: [
  
    CommonModule,
    FormsModule,
    VendorRoutingModule,
    FontAwesomeModule
  ]
})
export class VendorModule { }
