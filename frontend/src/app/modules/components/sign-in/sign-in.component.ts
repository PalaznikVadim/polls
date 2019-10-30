import { Component, OnInit } from '@angular/core';
import {UserModel} from "../../models/user.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  user=new UserModel();

  signinForm : FormGroup ;

  constructor() { }

  ngOnInit() {
    this.signinForm= new FormGroup({
      email: new FormControl("",[
        Validators.required,
        Validators.pattern("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}")
        ]
      ),
      password: new FormControl("",[
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(25)
      ]),
      isRemember: new FormControl()
    });
  }

  isControlInvalid(controlName: string): boolean {
    const control = this.signinForm.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  signIn_Click(){

  this.user={
    DOB: undefined,
    name: "",
    surname: "",
    email:this.signinForm.controls['email'].value,
    password:this.signinForm.controls['password'].value,
    isRemember:this.signinForm.controls['isRemember'].value
  };
    console.log(this.user);

}

}
