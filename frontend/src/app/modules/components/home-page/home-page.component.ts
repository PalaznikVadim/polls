import {Component, OnInit, OnDestroy, TemplateRef} from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import {PollModel} from "../../models/poll.model";
import {PollService} from "../../../services/poll.service";
import {UserService} from "../../../services/user.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  role:string;

  constructor(private userService:UserService) {
  }

  ngOnInit(): void {
    this.role=this.userService.currUser.role;
  }

}
