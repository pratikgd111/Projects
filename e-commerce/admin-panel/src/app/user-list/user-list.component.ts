import { UsersService } from './../users.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
})
export class UserListComponent implements OnInit {
  users = [];
  constructor(private userService: UsersService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers() {
    const request = this.userService.getUsers();
    request.subscribe((response) => {
      if (response['status'] == 'success') {
        this.users = response['data'];
        console.log(this.users);
      } else {
        console.error(response['error']);
      }
    });
  }

  toggleActive(user) {
    this.userService.toggleUserActiveStatus(user).subscribe((response) => {
      if (response['status'] == 'success') {
        this.loadUsers();
      } else {
        console.error(response['error']);
      }
    });
  }
}
