import { ClientService } from './../client.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-profile-card',
  templateUrl: './user-profile-card.component.html',
  styleUrls: ['./user-profile-card.component.css'],
})
export class UserProfileCardComponent implements OnInit {
  fullname = '';
  first_name = '';
  email = '';
  mobile = 0;
  age = 0;
  gender = '';
  photo = '';
  userId;
  street_address1;
  street_address2;
  state;
  city;
  zipcode;
  constructor(private clientService: ClientService) {}

  //  this.clientService.getAllDomains().subscribe((response:[])=>{
  //     this.domains=response
  //     console.log(this.domains);
  // }, (error)=>{
  //   console.log(error);
  // })

  ngOnInit(): void {
    this.loadUserDetails();
  }

  loadUserDetails() {
    this.clientService.getUserDetail().subscribe(
      (response) => {
        console.log(response);
        this.first_name = response['firstName'];
        console.log('first name ' + this.first_name);
        this.fullname = response['firstName'] + ' ' + response['lastName'];
        this.age = response['age'];
        this.email = response['email'];
        this.gender = response['gender'];
        this.photo = response['photo'];
        this.street_address1 = response['streetadd1'];
        this.street_address2 = response['streetadd2'];
        this.state = response['state'];
        this.city = response['city'];
        this.zipcode = response['pincode'];
        this.mobile = response['mobile']
      },
      (error) => {
        console.log('error in user profile card' + error);
      }
    );
  }
  // check profileExist
}
