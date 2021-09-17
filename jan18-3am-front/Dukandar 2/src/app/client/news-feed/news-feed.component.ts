import { Component, OnInit } from '@angular/core';
import {faMapMarker,faHeart,faThumbsUp,faSearch,faUser,faBell,faHome,faHeartBroken } from '@fortawesome/free-solid-svg-icons';
import { ClientService } from '../client.service';

@Component({
  selector: 'app-news-feed',
  templateUrl: './news-feed.component.html',
  styleUrls: ['./news-feed.component.css']
})
export class NewsFeedComponent implements OnInit {
  imagepath= ['https://post.healthline.com/wp-content/uploads/2020/07/pizza-beer-1200x628-facebook-1200x628.jpg','https://post.healthline.com/wp-content/uploads/2020/07/pizza-beer-1200x628-facebook-1200x628.jpg','https://post.healthline.com/wp-content/uploads/2020/07/pizza-beer-1200x628-facebook-1200x628.jpg','https://post.healthline.com/wp-content/uploads/2020/07/pizza-beer-1200x628-facebook-1200x628.jpg']
  shopimage= ['https://jooinn.com/images/shop-10.jpg','https://jooinn.com/images/shop-10.jpg','https://jooinn.com/images/shop-10.jpg','https://jooinn.com/images/shop-10.jpg']
  shopname= ['Oscar Hotel', 'Chandu mall','Ravi saloon','Shide Gym']

  homeshopinfo:any[]=[]

  rating=3

  radius= ['5 km' , '8 km' , '10km']
  domain= ['Hotel' , 'Grocery' , 'Saloon', 'Gym']

  isFev=0

  faMapMarker=faMapMarker;
  faHeart=faHeart;
  faThumbsUp=faThumbsUp;
  faSearch=faSearch;
  faUser=faUser;
  faBell=faBell;
  faHome=faHome;
  faHeartBroken=faHeartBroken;

  constructor(
    private clientservice:ClientService
  ) { }

  ngOnInit(): void {
    this.clientservice.getAllShopsPosts().subscribe((response:[])=>{
      console.log(response);
      this.homeshopinfo=response
    })
  }


  like(shopid:any ){
    this.clientservice.likeincrerement(shopid).subscribe((response)=>{

      console.log(response);

      window.location.reload();
    })
    
  }

  addtofev(shopid:any){

    this.clientservice.addToFev(shopid).subscribe((response)=>{

      this.isFev=1
      window.location.reload();
    })
  }

}
