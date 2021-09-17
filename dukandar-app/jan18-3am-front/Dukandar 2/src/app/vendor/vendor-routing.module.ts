import { AuthGuardService } from './../authguard.service';
import { EnquiryComponent } from './enquiry/enquiry.component';
import { ShopPhotoComponent } from './shop-photo/shop-photo.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule, CanActivate } from '@angular/router';
import { VendorregisterComponent } from './vendorregister/vendorregister.component';
import { ShowprofileComponent } from './showprofile/showprofile.component';
import { ShowpostsComponent } from './showposts/showposts.component';
import { AddpostComponent } from './addpost/addpost.component';
import { ReviewsComponent } from './reviews/reviews.component';
import { PersonalinfoComponent } from './personalinfo/personalinfo.component';

const routes: Routes = [
  { path: 'vendorregister', component: VendorregisterComponent, canActivate: [AuthGuardService] },

  { path: 'shop-photo', component: ShopPhotoComponent, canActivate: [AuthGuardService] },

  {
    path: 'showprofile', component: ShowprofileComponent, canActivate: [AuthGuardService],
    children: [
      { path: '', redirectTo: '/vendor/showprofile/personalinfo', pathMatch: 'full' },
      { path: 'personalinfo', component: PersonalinfoComponent, canActivate: [AuthGuardService] },
      { path: 'showposts', component: ShowpostsComponent, canActivate: [AuthGuardService] },
      { path: 'addpost', component: AddpostComponent, canActivate: [AuthGuardService] },
      { path: 'reviews', component: ReviewsComponent, canActivate: [AuthGuardService] },
      { path: 'gallery', component: ShopPhotoComponent, canActivate: [AuthGuardService] },
      { path: 'enquiry', component: EnquiryComponent, canActivate: [AuthGuardService] }
    ]
  }


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VendorRoutingModule { }
