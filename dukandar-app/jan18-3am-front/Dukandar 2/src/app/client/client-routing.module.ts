import { RadiussearchComponent } from './radiussearch/radiussearch.component';
import { SearchComponent } from './search/search.component';
import { ShopsAroundYouComponent } from './shops-around-you/shops-around-you.component';
import { NewsFeedComponent } from './news-feed/news-feed.component';
import { AddReviewComponent } from './add-review/add-review.component';
import { AddQueryComponent } from './add-query/add-query.component';
import { AuthGuardService } from './../authguard.service';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ShowpostsComponent } from './showposts/showposts.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ShopdetailsComponent } from './shopdetails/shopdetails.component';
import { ShopinfoComponent } from './shopinfo/shopinfo.component';
import { ReviewsComponent } from '../vendor/reviews/reviews.component';
import { ShopreviewsComponent } from './shopreviews/shopreviews.component';
import { UserProfileCardComponent } from './user-profile-card/user-profile-card.component';

const routes: Routes = [
  {
    path: 'profile-card',
    component: UserProfileCardComponent,
    canActivate: [AuthGuardService],
  },
  {
    path: 'profile',
    component: UserProfileComponent,
    canActivate: [AuthGuardService],
  },

  {
    path: 'shopdetails',
    component: ShopdetailsComponent,
    canActivate: [AuthGuardService],
    children: [
      {
        path: '',
        redirectTo: '/client/shopdetails/shopinfo',
        pathMatch: 'full',
        canActivate: [AuthGuardService],
      },
      {
        path: 'showposts',
        component: ShowpostsComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'shopreviews',
        component: ShopreviewsComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'shopinfo',
        component: ShopinfoComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'add-query',
        component: AddQueryComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'add-review',
        component: AddReviewComponent,
        canActivate: [AuthGuardService],
      },
    ],
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuardService],
    children: [
      { path: '', redirectTo: '/client/home/newsFeed', pathMatch: 'full' },
      {
        path: 'newsFeed',
        component: NewsFeedComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'shopsaroundyou',
        component: ShopsAroundYouComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'radiussearch',
        component: RadiussearchComponent,
        canActivate: [AuthGuardService],
      },
    ],
  },
  {
    path: 'search',
    component: SearchComponent,
    canActivate: [AuthGuardService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ClientRoutingModule {}
