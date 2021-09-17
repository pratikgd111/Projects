import { LocationService } from './../../vendor/location.service';
import { ActivatedRoute } from '@angular/router';
import { ClientService } from './../client.service';
import { Component, OnInit } from '@angular/core';
import { faClock,faMapMarker,faMobile,faSearch,faUser,faBell,faHome } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-shopinfo',
  templateUrl: './shopinfo.component.html',
  styleUrls: ['./shopinfo.component.css']
})
export class ShopinfoComponent implements OnInit {

  distance=5
  opentime='10:00'
  closetime='10:00'
  mobile=9478562134
  whyvisit='One thing that gets people into a store is the excitement that comes with seeing a great product or deal. That’s why treasure hunt experiences are so powerful (and addictive!). When people know that they’ll come across amazing offers and products and that those items won’t be around for long, shoppers will be more likely to convert on the spot.'


  faClock=faClock;
  faMapMarker=faMapMarker;
  faMobile=faMobile;
  faSearch=faSearch;
  faUser=faUser;
  faBell=faBell;
  faHome=faHome;

  latitude = ""
  longitude = ""

  shopInfo: any = "";

  shopId = "";


  constructor(
    private clientService: ClientService,
    private route: ActivatedRoute,
    private locationService: LocationService
  ) { }

  ngOnInit(): void {
       this.currentLocation()

  }


  currentLocation() {

    this.locationService.getLocatioService().then((response) => {
      this.latitude = response['lat']
      this.longitude = response['long']

      console.log('latitude', this.latitude);
      console.log('longitude', this.longitude);

      this.route.queryParams.subscribe(params => {
        console.log(this.route);

        console.log('shop id', params['shopId']);
        this.shopId = params['shopId'];


        this.clientService.getShopInfo(this.shopId, this.latitude, this.longitude).subscribe((response) => {
          console.log(response);
          this.shopInfo = response
          console.log("Hello world");
          


        })





      });





    });
  }




}
