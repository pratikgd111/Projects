import { ShopsAroundYouComponent } from './shops-around-you/shops-around-you.component';
import { NewsFeedComponent } from './news-feed/news-feed.component';
import { SearchComponent } from './search/search.component';
import { AddQueryComponent } from './add-query/add-query.component';
import { AddReviewComponent } from './add-review/add-review.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserProfileCardComponent } from './user-profile-card/user-profile-card.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {StarRatingModule} from 'angular-star-rating';
import { ShopdetailsComponent } from './shopdetails/shopdetails.component';
import { ShopinfoComponent } from './shopinfo/shopinfo.component';
import { ShowpostsComponent } from './showposts/showposts.component';
import { ShopreviewsComponent } from './shopreviews/shopreviews.component';
import { RadiussearchComponent } from './radiussearch/radiussearch.component';




@NgModule({
  declarations: [AddReviewComponent, ShopinfoComponent, ShowpostsComponent,  ShopreviewsComponent, AddQueryComponent, NewsFeedComponent, ShopsAroundYouComponent, SearchComponent, HomeComponent, UserProfileComponent, UserProfileCardComponent, ShopdetailsComponent, ShopinfoComponent ,ShowpostsComponent, ShopreviewsComponent, RadiussearchComponent],
  imports: [
    CommonModule,
    ClientRoutingModule,
    FormsModule,
    FontAwesomeModule,
    StarRatingModule.forRoot()
  ]
})
export class ClientModule { }
