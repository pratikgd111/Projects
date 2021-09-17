import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  images=['https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5LPkxiEUTWqaJOJbV5jqrvjhVrfd9uSCjKw&usqp=CAU','https://media.gettyimages.com/photos/empty-black-chairs-and-mirrors-in-barber-shop-picture-id1030255416?s=612x612','https://www.hashmicro.com/blog/wp-content/uploads/2018/08/How-to-Open-a-Successful-Pop-Up-Shop-EDITED.jpg','https://webstockreview.net/images/closet-clipart-cloth-shop-2.jpg',
  'https://images.unsplash.com/photo-1542838132-92c53300491e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80',
  'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5LPkxiEUTWqaJOJbV5jqrvjhVrfd9uSCjKw&usqp=CAU','https://media.gettyimages.com/photos/empty-black-chairs-and-mirrors-in-barber-shop-picture-id1030255416?s=612x612','https://www.hashmicro.com/blog/wp-content/uploads/2018/08/How-to-Open-a-Successful-Pop-Up-Shop-EDITED.jpg','https://webstockreview.net/images/closet-clipart-cloth-shop-2.jpg',
  'https://images.unsplash.com/photo-1542838132-92c53300491e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80',
  'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5LPkxiEUTWqaJOJbV5jqrvjhVrfd9uSCjKw&usqp=CAU','https://media.gettyimages.com/photos/empty-black-chairs-and-mirrors-in-barber-shop-picture-id1030255416?s=612x612','https://www.hashmicro.com/blog/wp-content/uploads/2018/08/How-to-Open-a-Successful-Pop-Up-Shop-EDITED.jpg','https://webstockreview.net/images/closet-clipart-cloth-shop-2.jpg',
  'https://images.unsplash.com/photo-1542838132-92c53300491e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80',
  'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5LPkxiEUTWqaJOJbV5jqrvjhVrfd9uSCjKw&usqp=CAU','https://media.gettyimages.com/photos/empty-black-chairs-and-mirrors-in-barber-shop-picture-id1030255416?s=612x612','https://www.hashmicro.com/blog/wp-content/uploads/2018/08/How-to-Open-a-Successful-Pop-Up-Shop-EDITED.jpg','https://webstockreview.net/images/closet-clipart-cloth-shop-2.jpg',
  'https://images.unsplash.com/photo-1542838132-92c53300491e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80',
  'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5LPkxiEUTWqaJOJbV5jqrvjhVrfd9uSCjKw&usqp=CAU','https://media.gettyimages.com/photos/empty-black-chairs-and-mirrors-in-barber-shop-picture-id1030255416?s=612x612','https://www.hashmicro.com/blog/wp-content/uploads/2018/08/How-to-Open-a-Successful-Pop-Up-Shop-EDITED.jpg','https://webstockreview.net/images/closet-clipart-cloth-shop-2.jpg',
  'https://images.unsplash.com/photo-1542838132-92c53300491e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80'
]
  radius= ['5 km' , '8 km' , '10km']
  domain= ['Hotel' , 'Grocery' , 'Saloon', 'Gym']
  
  constructor() { }

  ngOnInit(): void {
  }
  nearme(){

  }

  threeplusrating(){

  }

  offershotel(){

  }

  savefourtyplusper(){

  }
}
