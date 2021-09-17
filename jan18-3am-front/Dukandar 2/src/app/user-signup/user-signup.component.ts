import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClientService } from '../client/client.service';

@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrls: ['./user-signup.component.css']
})
export class UserSignupComponent implements OnInit {

  mobile='';
  password='';
  role="USER";

  constructor(
    private clientService:ClientService,
    private router:Router
  ) { }

  ngOnInit(): void {
  }

  onSubmit(){
    const body= {
      mobile:this.mobile,
      password:this.password,
      role:this.role
    }

    this.clientService.userSignup(body).subscribe((response)=>{
      console.log(response);
      alert("You have signed up successfully. please login again");
      this.router.navigate(['/login'])
      
  }, (error)=>{
    console.log(error);
    alert("something went wrong. Please try again......");
    
  })



  }

}
