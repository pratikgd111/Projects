import { Component, OnInit } from '@angular/core';
import { ClientService } from '../client.service';
import {faMapMarker,faHeart,faThumbsUp,faSearch,faUser,faBell,faHome,faHeartBroken } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-shops-around-you',
  templateUrl: './shops-around-you.component.html',
  styleUrls: ['./shops-around-you.component.css']
})
export class ShopsAroundYouComponent implements OnInit {

  homeshopinfo:any[]=[]

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
    // this.clientservice.getHomeshopInfos().subscribe((response:[])=>{
    //   console.log(response);
    //   this.homeshopinfo=response
    // })
  }

}
