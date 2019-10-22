import { Component, OnInit } from '@angular/core';
import {UserModel} from "../../models/user.model";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  user=new UserModel();

  constructor() { }

  ngOnInit() {
  }

  signIn_Click(){
  console.log(this.user);
}

}
