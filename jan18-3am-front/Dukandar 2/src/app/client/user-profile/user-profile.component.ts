import { ClientService } from './../client.service';
//import { LocationService } from './../location.service';
import { HttpClient } from '@angular/common/http';
import { Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LocationService } from 'src/app/vendor/location.service';
import {
  faBell,
  faHeart,
  faHome,
  faMapMarker,
  faSearch,
  faThumbsUp,
  faUser,
} from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent implements OnInit {
  selectedFile: File;
  photo;

  firstName = '';
  lastName = '';
  mobileNo ;
  email = '';
  gender = '';
  age = 0;

  longitude = 0;
  latitude = 0;

  streetadd1 = '';
  streetadd2 = '';
  city = '';
  state = '';
  pincode = 0;

  faMapMarker = faMapMarker;
  faHeart = faHeart;
  faThumbsUp = faThumbsUp;
  faSearch = faSearch;
  faUser = faUser;
  faBell = faBell;
  faHome = faHome;

  constructor(
    private router: Router,
    private userservice: ClientService,
    private locationService: LocationService,
    private httpclient: HttpClient
  ) { }

  ngOnInit(): void {
    this.userservice.getUserDetail().subscribe(
      (response) => {
        console.log(response);
        this.firstName = response['firstName'];
        this.lastName = response['lastName'];
        this.age = response['age'];
        this.email = response['email'];
        this.gender = response['gender'];
        this.photo = response['photo'];
        this.streetadd1 = response['streetadd1'];
        this.streetadd2 = response['streetadd2'];
        this.state = response['state'];
        this.city = response['city'];
        this.pincode = response['pincode'];
        this.mobileNo = response['mobile'];
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
    this.userservice
      .uploadUserImage(this.selectedFile)
      .subscribe((response) => {
        this.photo = response['photo'];
        console.log('response', response);
      });
  }

  SaveAddressInfo() { }
  // SaveAddressInfo() {
  //   let key = 'hZVduq1W62YgxFmD64zpEcLJzeNA2Y4M';
  //   let location = '';
  //   location =
  //     this.streetadd1 +
  //     ',' +
  //     this.streetadd2 +
  //     ',' +
  //     this.city +
  //     ',' +
  //     this.state;
  //   let url = `http://www.mapquestapi.com/geocoding/v1/address?key=${key}&location=${location}`;

  //   this.httpclient.get(url).subscribe((response) => {
  //     this.latitude = response['results'][0].locations[0].latLng.lat;
  //     this.longitude = response['results'][0].locations[0].latLng.lng;

  //     console.log(this.latitude);
  //     console.log(this.longitude);
  //   });

  //   this.userservice.saveaddressinfo(
  //     this.streetadd1,
  //     this.streetadd2,
  //     this.city,
  //     this.state,
  //     this.pincode,
  //     this.longitude,
  //     this.latitude
  //   );
  // }

  currentLocation() {
    console.log('hello');

    this.locationService.getLocatioService().then((resp) => {
      console.log(resp.long);
      console.log(resp.lat);
    });
  }

  onSubmit() {
    let key = 'hZVduq1W62YgxFmD64zpEcLJzeNA2Y4M';
    let location = '';
    location =
      this.streetadd1 +
      ',' +
      this.streetadd2 +
      ',' +
      this.city +
      ',' +
      this.state +
      this.pincode;
    let url = `http://www.mapquestapi.com/geocoding/v1/address?key=${key}&location=${location}`;

    this.httpclient.get(url).subscribe((response) => {
      this.latitude = response['results'][0].locations[0].latLng.lat;
      this.longitude = response['results'][0].locations[0].latLng.lng;

      console.log(this.latitude);
      console.log(this.longitude);

      this.userservice
        .saveUserinfo(
          this.firstName,
          this.lastName,
          this.mobileNo,
          this.email,
          this.gender,
          this.age,
          this.streetadd1,
          this.streetadd2,
          this.city,
          this.state,
          this.pincode,
          this.longitude,
          this.latitude
        )
        .subscribe((response) => {
          //this.photo = response['photo'];
          console.log('response', response);
        });
      this.router.navigate(['/client/profile-card']);








    });


  }
}
