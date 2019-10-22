import { Component, OnInit } from '@angular/core';
import {UserModel} from "../../models/user.model";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user=new UserModel();

  constructor() { }

  ngOnInit() {
  }

  createUser() {
    console.log(this.user);
  }
}
