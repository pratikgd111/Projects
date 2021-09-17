import { ClientService } from './../client.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-showposts',
  templateUrl: './showposts.component.html',
  styleUrls: ['./showposts.component.css']
})
export class ShowpostsComponent implements OnInit {

  offerimage = 'https://magicpin-image-assets.s3-ap-southeast-1.amazonaws.com/Remarketing-Pics/Rmkt/MO_NB+_F%26B.jpg'
  Offername = 'Non-veg offer'
  discount = '50%'
  Offerlastto = '23-jan-2021'
  like = 500
  dislike = 100

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private clientService: ClientService
  ) { }


  shopId = "";
  posts: any = ""


  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      console.log(this.route);

      console.log('shop id', params['shopId']);
      this.shopId = params['shopId'];


      this.clientService.getAllPosts(this.shopId).subscribe((response) => {
        console.log(response);
        this.posts = response


      })

    })

  }

}
