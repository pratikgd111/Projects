import { VendorserviceService } from './../vendorservice.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-personalinfo',
  templateUrl: './personalinfo.component.html',
  styleUrls: ['./personalinfo.component.css']
})
export class PersonalinfoComponent implements OnInit {

  firstName = "";
  lastName = "";
  email = "";
  mobile = "";
  age = "";
  gender ="";
 

  constructor(
    private vendorservice:VendorserviceService
  ) { }

  ngOnInit(): void {

    this.vendorservice.loadPersonalInfo().subscribe((response) =>{

      console.log(response);

      this.firstName = response['firstName']
      this.lastName = response['lastName']
      this.email = response['email']
      this.mobile = response['mobile']
      this.age = response['age']
      this.gender = response['gender']
    }
    )




  }

}
