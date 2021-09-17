import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faClock, faMapMarker, faMobile, faSearch, faUser, faBell, faHome } from '@fortawesome/free-solid-svg-icons';
import { LocationService } from 'src/app/vendor/location.service';
import { ClientService } from '../client.service';


@Component({
  selector: 'app-shopdetails',
  templateUrl: './shopdetails.component.html',
  styleUrls: ['./shopdetails.component.css']
})
export class ShopdetailsComponent implements OnInit {


  faClock = faClock;
  faMapMarker = faMapMarker;
  faMobile = faMobile;
  faSearch = faSearch;
  faUser = faUser;
  faBell = faBell;
  faHome = faHome;


  shopInfo: any = "";

  shopId = "";

  constructor(
    private clientService: ClientService,
    private route: ActivatedRoute,
    private locationService: LocationService,
    private router: Router,
  ) { }


  latitude = ""
  longitude = ""


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

          console.log(this.shopInfo[3]);
          


        })





      });





    });
  }


  OnLogout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }


}
