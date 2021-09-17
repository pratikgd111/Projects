import { HttpClient, HttpHandler } from '@angular/common/http';
// import { Router } from '@angular/router';
// import { LocationService } from './../../vendor/location.service';
// import { Component, OnInit } from '@angular/core';
// import { faMapMarker, faHeart, faThumbsUp, faSearch, faUser, faBell, faHome } from '@fortawesome/free-solid-svg-icons';
// import { ClientService } from '../client.service';


// @Component({
//   selector: 'app-home',
//   templateUrl: './home.component.html',
//   styleUrls: ['./home.component.css']
// })
// export class HomeComponent implements OnInit {


//   imagepath = ['https://post.healthline.com/wp-content/uploads/2020/07/pizza-beer-1200x628-facebook-1200x628.jpg', 'https://post.healthline.com/wp-content/uploads/2020/07/pizza-beer-1200x628-facebook-1200x628.jpg', 'https://post.healthline.com/wp-content/uploads/2020/07/pizza-beer-1200x628-facebook-1200x628.jpg', 'https://post.healthline.com/wp-content/uploads/2020/07/pizza-beer-1200x628-facebook-1200x628.jpg']
//   shopimage = ['https://jooinn.com/images/shop-10.jpg', 'https://jooinn.com/images/shop-10.jpg', 'https://jooinn.com/images/shop-10.jpg', 'https://jooinn.com/images/shop-10.jpg']
//   shopname = ['Oscar Hotel', 'Chandu mall', 'Ravi saloon', 'Shide Gym']

//   popularimage = ['https://jooinn.com/images/shop-10.jpg', 'https://jooinn.com/images/shop-10.jpg', 'https://jooinn.com/images/shop-10.jpg', 'https://jooinn.com/images/shop-10.jpg',
//     'https://jooinn.com/images/shop-10.jpg', 'https://jooinn.com/images/shop-10.jpg', 'https://jooinn.com/images/shop-10.jpg', 'https://jooinn.com/images/shop-10.jpg']

//   // create a object of single above three and use it in ngfor of center card

//   radius="";
//   domain = ['Hotel', 'Grocery', 'Saloon', 'Gym']


//   faMapMarker = faMapMarker;
//   faHeart = faHeart;
//   faThumbsUp = faThumbsUp;
//   faSearch = faSearch;
//   faUser = faUser;
//   faBell = faBell;
//   faHome = faHome;


//   shops = []

//   latitude = "";
//   longitude = "";


//   constructor(
//     private locationService: LocationService,
//     private clientService: ClientService,
//     private router:Router
//   ) { }

//   ngOnInit(): void {

//   }

//   onSearch() {
//     this.currentLocation();
//   }


//   currentLocation() {

//     this.locationService.getLocatioService().then((response) => {
//       this.latitude = response['lat']
//       this.longitude = response['long']

//       console.log('latitude', this.latitude);
//       console.log('longitude', this.longitude);

//       console.log(this.radius);

//       this.clientService.loadShopsWithinRadius(this.latitude, this.longitude,this.radius)
//       .subscribe(
//         (response:[]) => {
//           console.log(response);
//           this.shops= response

//           console.log(this.shops[0][3]?.length > 0);

//         },
//         (error) => {
//           alert("Something Went Wrong")
//           console.log(error);
//         }
//       );
//     });
//   }





//   top() {
//     window.scrollTo(0, 0);
//   }

//   nearme() {

//   }

//   threeplusrating() {

//   }

//   offershotel() {

//   }

//   savefourtyplusper() {

//   }




//   onProfile() {
//     this.clientService.getProfile().subscribe(
//       (response) => {
//         console.log('response', response);
//         console.log('user Details ' + response['userId']);
//       },
//       (error) => {
//         console.log('error:' + error);
//       }
//     );
//   }




// OnLogout() {
//   localStorage.removeItem('token');
//   this.router.navigate(['/login']);
// }


// }




import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { faMapMarker, faHeart, faThumbsUp, faSearch, faUser, faBell, faHome, faHeartBroken } from '@fortawesome/free-solid-svg-icons';
import { ClientService } from '../client.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {



  popularimage = ['https://jooinn.com/images/shop-10.jpg', 'https://jooinn.com/images/shop-10.jpg', 'https://jooinn.com/images/shop-10.jpg', 'https://jooinn.com/images/shop-10.jpg',
    'https://jooinn.com/images/shop-10.jpg', 'https://jooinn.com/images/shop-10.jpg', 'https://jooinn.com/images/shop-10.jpg', 'https://jooinn.com/images/shop-10.jpg']

  homeshopinfo: any[] = []

  radius:number = 0;

  isFev = 0


  faMapMarker = faMapMarker;
  faHeart = faHeart;
  faThumbsUp = faThumbsUp;
  faSearch = faSearch;
  faUser = faUser;
  faBell = faBell;
  faHome = faHome;
  faHeartBroken = faHeartBroken;

  constructor(
    private clientservice: ClientService,
    private router:Router
  ) { }

  ngOnInit(): void {
    // this.clientservice.getHomeshopInfos().subscribe((response: []) => {
    //   console.log(response);
    //   this.homeshopinfo = response
    // })
  }





  onSearch() {
    console.log("in fun");

    this.clientservice.radius=this.radius
    this.router.navigate(['/client/home/radiussearch'])

  }


  onSearchAgain(){
    this.router.navigate(['/client/home'])
  }




  top() {
    window.scrollTo(0, 0);
  }

  nearme() {

  }

  threeplusrating() {

  }

  offershotel() {

  }

  savefourtyplusper() {

  }


  OnLogout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }


  onProfile() {
    this.clientservice.getProfile().subscribe(
      (response) => {
        console.log('response', response);
        console.log('user Details ' + response['userId']);
      },
      (error) => {
        console.log('error:' + error);
      }
    );
  }


  

}