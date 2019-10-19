import { Component, OnInit } from '@angular/core';
import {User} from "../../User";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user=new User();
  password:string;


  constructor() { }

  ngOnInit() {
  }

  createUser() {
    console.log(this.user);
    console.log(this.password)
  }
}
