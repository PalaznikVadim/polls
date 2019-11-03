import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserModel} from "../../models/user.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PollService} from "../../../services/poll.service";
import {UserService} from "../../../services/user.service";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit,OnDestroy {

  errorMassage:string;
  user = new UserModel();
  sub: any;
  signinForm: FormGroup;

  constructor(private  userService: UserService) {
  }

  ngOnInit() {
    this.signinForm = new FormGroup({
      email: new FormControl("", [
          Validators.required,
          Validators.pattern("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}")
        ]
      ),
      password: new FormControl("", [
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(25)
      ]),
      isRemember: new FormControl()
    });
  }

  ngOnDestroy(): void {
   // this.sub.unsubscribe();
  }

  isControlInvalid(controlName: string): boolean {
    const control = this.signinForm.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  signInClick() {

    this.sub = this.userService.getUserByEmailAndPassword(this.signinForm.controls['email'].value,this.signinForm.controls['password'].value).subscribe(value => {

      this.user = value as UserModel;

      if(this.user!==null){
        this.errorMassage=null;
      }else{
        this.errorMassage='Incorrect data. Recheck entered data'
      }
      console.log(this.user);

    });

    /*this.user={
      DOB: undefined,
      name: "",
      surname: "",
      email:this.signinForm.controls['email'].value,
      password:this.signinForm.controls['password'].value,
      isRemember:this.signinForm.controls['isRemember'].value
    };*/



  }
}
