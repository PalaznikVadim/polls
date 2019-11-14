import { Component, OnInit } from '@angular/core';
import {UserModel} from "../../../models/user.model";
import {UserService} from "../../../../services/user.service";
import {PollService} from "../../../../services/poll.service";
import {PollModel} from "../../../models/poll.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users:UserModel[];
  subs:any[];
  countSubs=0;

  constructor(private userService:UserService,private pollService:PollService,private router:Router) { }

  ngOnInit() {
    this.subs=[];
    this.subs[this.countSubs++]=this.userService.getAllUsers().subscribe(users=>{
      this.users=users;
    });

  }


  goTo(id: number) {
    localStorage.setItem('idSelectedUser',id.toString());
    this.router.navigate(['profile'])
  }
}
