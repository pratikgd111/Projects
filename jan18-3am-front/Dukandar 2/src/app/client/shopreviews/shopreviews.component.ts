import { ClientService } from './../client.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-shopreviews',
  templateUrl: './shopreviews.component.html',
  styleUrls: ['./shopreviews.component.css']
})
export class ShopreviewsComponent implements OnInit {


  image='https://miro.medium.com/max/1200/1*mk1-6aYaf_Bes1E3Imhc0A.jpeg'
  review='Lorem Ipsum is simply dummy text of the pr make  but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.'
  timesago='1 hour ago'
  name='Bhalchandra shinde'
 
  constructor(

    private router: Router,
    private route: ActivatedRoute,
    private clientService: ClientService
  ) { }


    shopId="";
    reviews:any="";


  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      console.log(this.route);

      console.log('shop id', params['shopId']);
      this.shopId = params['shopId'];


      this.clientService.getAllReviews(this.shopId).subscribe((response) => {
        console.log(response);
        this.reviews = response


      })

    })




  }

}
