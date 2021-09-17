import { LocationService } from './../location.service';
import { HttpClient } from '@angular/common/http';
import { Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { VendorserviceService } from '../vendorservice.service';
import { Router } from '@angular/router';
import {
  faMapMarker,
  faHeart,
  faThumbsUp,
  faSearch,
  faUser,
  faBell,
  faHome,
} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-vendorregister',
  templateUrl: './vendorregister.component.html',
  styleUrls: ['./vendorregister.component.css'],
})
export class VendorregisterComponent implements OnInit {

  selectedFile: File;
  photo;

  firstName = '';
  lastName = '';
  mobile = 0;
  email = '';
  gender = '';
  age = 0;

  shopName = '';
  businesscategory = '';
  //category = ''
  speciality = '';
  openingtime = '';
  closingtime = '';
  reasontoVisit = '';
  longitude = 0;
  latitude = 0;

  streetadd1 = '';
  streetadd2 = '';
  city = '';
  state = '';
  pincode = '';

  faMapMarker = faMapMarker;
  faHeart = faHeart;
  faThumbsUp = faThumbsUp;
  faSearch = faSearch;
  faUser = faUser;
  faBell = faBell;
  faHome = faHome;

  constructor(
    private router: Router,
    private vendorservice: VendorserviceService,
    private locationService: LocationService,
    private httpclient: HttpClient
  ) { }

  ngOnInit(): void {

    this.vendorservice.loadPersonalInfo().subscribe(
      (response) => {

        console.log(response);
        this.firstName = response['firstName']
        this.lastName = response['lastName']
        this.mobile = response['mobile']
        this.email = response['email']
        this.age = response['age']
        this.firstName = response['firstName']
        this.photo = response['photo']

      },
      (error) => {
        console.log(error);
      }
    );


    this.vendorservice.loadShopInfo().subscribe(
      (response) => {

        console.log(response);
        this.shopName = response['shopName'];
        this.openingtime = response['opening_time'];
        this.closingtime = response['closing_time'];
        this.reasontoVisit = response['reasontoVisit'];
        this.speciality = response['speciality'];



      },
      (error) => {
        console.log(error);
      }
    );

    this.vendorservice.loadDomainInfo().subscribe(
      (response) => {

        console.log(response);
        this.businesscategory = response['string']

      },
      (error) => {
        console.log(error);
      }
    )

    this.vendorservice.getShopAddress().subscribe(
      (response) => {

        console.log(response);
        this.pincode = response['pincode']
        this.city = response['city']
        this.state = response['state']
        this.streetadd1 = response['streetadd1']
        this.streetadd2 = response['streetadd2']

      },
      (error) => {
        console.log(error);
      }
    )

  }

  onFileChanged(event) {
    this.selectedFile = event.target.files[0];
  }

  onUpload() {
    this.vendorservice
      .uploadVendorImage(this.selectedFile)
      .subscribe((response) => {
        this.photo = response['photo'];
        console.log('response', response);
      });
  }

  SavePersonalInfo() {
    this.vendorservice
      .savepersonalinfo(this.firstName, this.lastName, this.mobile, this.email, this.gender, this.age)
      .subscribe(
        (response) => {
          alert("Saved Successfully")
          console.log(response);
        },
        (error) => {
          alert("Something Went Wrong")
          console.log(error);
        }
      );
  }


  SaveShopDetails() {
    this.vendorservice
      .saveshopdetails(this.shopName, this.speciality, this.openingtime, this.closingtime, this.reasontoVisit, this.businesscategory)
      .subscribe(
        (response) => {
          alert("Saved Successfully")
          console.log(response);
        },
        (error) => {
          alert("Something Went Wrong")
          console.log(error);
        }
      );

  }

  SaveAddressInfo() {
    let key = 'hZVduq1W62YgxFmD64zpEcLJzeNA2Y4M'
    let location = ''
    location = this.streetadd1 + ',' + this.streetadd2 + ',' + this.city + ',' + this.state
    let url = `http://www.mapquestapi.com/geocoding/v1/address?key=${key}&location=${location}`

    this.httpclient.get(url).subscribe((response) => {
      this.latitude = response['results'][0].locations[0].latLng.lat
      this.longitude = response['results'][0].locations[0].latLng.lng
      this.vendorservice
        .saveaddressinfo(this.streetadd1, this.streetadd2, this.city, this.state, this.pincode, this.longitude, this.latitude)
        .subscribe(
          (response) => {
            alert("Saved Successfully")
            console.log(response);
          },
          (error) => {
            alert("Something Went Wrong")
            console.log(error);
          }
        );
    })
  }






  currentLocation() {
    console.log('hello');

    this.locationService.getLocatioService().then((response) => {
      this.latitude = response['lat']
      this.longitude = response['long']
      this.vendorservice
      .saveaddressinfo(this.streetadd1, this.streetadd2, this.city, this.state, this.pincode, this.longitude, this.latitude)
      .subscribe(
        (response) => {
          alert("Saved Successfully")
          console.log(response);
        },
        (error) => {
          alert("Something Went Wrong")
          console.log(error);
        }
      );
    });
  }

  onSubmit() {
    this.router.navigate(['/vendor/shop-photo']);
  }


  OnLogout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }



}
