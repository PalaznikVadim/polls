import { Component, OnInit } from '@angular/core';
import {User} from "../../User";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  user=new User();

  constructor() { }

  ngOnInit() {
  }

  signIn_Click(){
  console.log(this.user);
}

}
