import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {PageChangedEvent} from "ngx-bootstrap";
import {RestPageModel} from "../../models/rest-page.model";
import {UserModel} from "../../models/user.model";
import {UserService} from "../../../services/user.service";
import {PollService} from "../../../services/poll.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit, OnDestroy {

  page: RestPageModel;
  users: UserModel[];
  subs: any[];
  currentPage = 1;
  sort: string = 'name';
  size: number;
  order: string;
  search: string;

  constructor(private userService: UserService, private pollService: PollService, private router: Router) {
  }

  ngOnInit() {
    this.search = '';
    this.order = 'ASC';
    console.log('currUser', this.userService.currUser);
    this.subs = [];
    this.size = 10;
    this.getUsers(1);
  }


  goTo(id: number) {
    this.userService.idSelectedUser = id;
    this.router.navigate(['userPolls'])
  }

  pageChanged(event: PageChangedEvent) {
    this.currentPage = event.page;
    this.getUsers(this.currentPage);
  }

  sortByName() {
    this.sort = 'name';
    this.getUsers(1);
  }

  sortBySurname() {
    this.sort = 'surname';
    this.getUsers(1);
  }

  sortByDate() {
    this.sort = 'dateOfBirth';
    this.getUsers(1);
  }

  getUsers(page:number) {
    this.currentPage=page;
    this.subs[this.subs.length] = this.userService.getAllUsers(this.search.trim(), this.currentPage - 1, this.size,
      this.sort, this.order).subscribe(page => {
      this.page = page as RestPageModel;
      this.users = this.page.content;
    });
  }

  ngOnDestroy(): void {
    if (this.subs.length != 0)
      for (let i = 0; i < this.subs.length; i++) {
        this.subs[i].unsubscribe();
      }
  }

  exit() {
    localStorage.removeItem('token');
    this.userService.currUser=null;
    this.router.navigate(['/']);
  }
}
