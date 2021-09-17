// import { Router } from '@angular/router';
// import { Component, OnInit } from '@angular/core';
// import {faMapMarker,faHeart,faThumbsUp,faSearch,faUser,faBell,faHome } from '@fortawesome/free-solid-svg-icons';


// @Component({
//   selector: 'app-showprofile',
//   templateUrl: './showprofile.component.html',
//   styleUrls: ['./showprofile.component.css']
// })
// export class ShowprofileComponent implements OnInit {

//   profilepic='https://expertphotography.com/wp-content/uploads/2020/07/instagram-profile-picture-size-guide-3.jpg'
//   shopdetails='Pure Non veg Hotel'
//   address='Pune,Shivajinagr'



//   faMapMarker=faMapMarker;
//   faHeart=faHeart;
//   faThumbsUp=faThumbsUp;
//   faSearch=faSearch;
//   faUser=faUser;
//   faBell=faBell;
//   faHome=faHome;

//   constructor(
//     private router:Router
//   ) { }

//   ngOnInit(): void {
//   }


//   OnLogout(){
//     localStorage.removeItem('token');
//     this.router.navigate(['/login']);
//   }

// }






import { Router } from '@angular/router';
import { VendorserviceService } from './../vendorservice.service';
import { Component, OnInit } from '@angular/core';
import { faMapMarker, faHeart, faThumbsUp, faSearch, faUser, faBell, faHome } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-showprofile',
  templateUrl: './showprofile.component.html',
  styleUrls: ['./showprofile.component.css']
})
export class ShowprofileComponent implements OnInit {

  profilepic = 'https://expertphotography.com/wp-content/uploads/2020/07/instagram-profile-picture-size-guide-3.jpg'
  firstName = ''
  lastName = ''

  shopname = ""
  opentime = ''
  clostime = ''
  speciality = ''
  reasontovisit = ''

  street1 = ''
  street2 = ''
  city = ''
  state = ''
  zip = 0


  faMapMarker = faMapMarker;
  faHeart = faHeart;
  faThumbsUp = faThumbsUp;
  faSearch = faSearch;
  faUser = faUser;
  faBell = faBell;
  faHome = faHome;

  constructor(
    private vendorservice: VendorserviceService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.vendorservice.loadPersonalInfo().subscribe((response) => {
      this.firstName = response['firstName']
      this.lastName = response['lastName']
      this.profilepic = response['photo']
    })


    this.vendorservice.getshopInfo().subscribe((response) => {

      console.log(response);

      this.opentime = response['opening_time']
      this.clostime = response['closing_time']
      this.speciality = response['speciality']
      this.shopname = response['shopName']
      this.reasontovisit = response['reasontoVisit']
    })

    this.vendorservice.getShopAddress().subscribe((response) => {
      console.log(response);

      this.street1 = response['streetadd1']
      this.street2 = response['streetadd2']
      this.city = response['city']
      this.state = response['state']
      this.zip = response['pincode']

    })

  }


  OnLogout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }




}
