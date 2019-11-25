import { Component, OnInit } from '@angular/core';
import {UserModel} from "../../../models/user.model";
import {UserService} from "../../../../services/user.service";
import {PollService} from "../../../../services/poll.service";
import {PollModel} from "../../../models/poll.model";
import {Router} from "@angular/router";
import {RestPageModel} from "../../../models/rest-page.model";
import {PageChangedEvent} from "ngx-bootstrap";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  page: RestPageModel;
  users: UserModel[];
  subs: any[];
  countSubs = 0;
  currentPage = 1;
  sort: string = 'name';
  size: number;
  prevSize: number = 5;

  constructor(private userService: UserService, private pollService: PollService, private router: Router) {
  }

  ngOnInit() {
    this.subs = [];
    this.size = 5;
    this.getUsers();
  }


  goTo(id: number) {
    localStorage.setItem('idSelectedUser', id.toString());
    this.router.navigate(['profile'])
  }

  pageChanged(event: PageChangedEvent) {
    this.currentPage = event.page;
    this.getUsers();
  }

  sortByName() {
    this.sort = 'name';
    this.getUsers();
  }

  sortBySurname() {
    this.sort = 'surname';
    this.getUsers();
  }

  sortByDate() {
    this.sort = 'dateOfBirth';
    this.getUsers();
  }

  getUsers() {
    this.subs[this.countSubs++] = this.userService.getAllUsers(this.currentPage - 1, this.size, this.sort, 'desk').subscribe(page => {
      this.page = page as RestPageModel;
      console.log(this.page);
      this.users = this.page.content as UserModel[];
      console.log(this.users)
    });
  }

  selectSize() {
    if (this.prevSize != this.size) {
      this.currentPage=1;
      this.getUsers();
      this.prevSize = this.size;
    }
  }
}
