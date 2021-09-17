import { ClientService } from './../client.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-query',
  templateUrl: './add-query.component.html',
  styleUrls: ['./add-query.component.css']
})
export class AddQueryComponent implements OnInit {
  query = ''


  shopId = "";

  constructor(private router: Router,
    private clientService: ClientService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      console.log(this.route);

      console.log('shop id', params['shopId']);
      this.shopId = params['shopId'];

    })
  }

  addQuery(){
    this.clientService.saveQuery(this.query, this.shopId).subscribe((response)=>{
      console.log(response);
      
    })
}

}
