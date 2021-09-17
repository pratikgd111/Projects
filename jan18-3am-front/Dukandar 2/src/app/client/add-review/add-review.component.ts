import { ClientService } from './../client.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit {
  comment=''
  rating=''


  shopId = "";

  constructor(private router:Router, 
      private clientService:ClientService,
      private route:ActivatedRoute
    ) { }

  ngOnInit(): void {

    this.route.queryParams.subscribe(params => {
      console.log(this.route);

      console.log('shop id', params['shopId']);
      this.shopId = params['shopId'];

      })

  }


  addReview(){
      this.clientService.saveReview(this.comment, this.rating, this.shopId).subscribe((response)=>{
        console.log(response);
        
      })
  }

}
