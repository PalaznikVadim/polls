import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserModel} from "../../models/user.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PollService} from "../../../services/poll.service";
import {UserService} from "../../../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit,OnDestroy {

  errorMassage:string;
  sub: any;
  signInForm: FormGroup;
  errors:Map<string,Array<string>>;

  constructor(private  userService: UserService,private router: Router,private pollService:PollService) {
  }

  ngOnInit() {
    this.signInForm = new FormGroup({
      email: new FormControl("", [
        Validators.required,
        Validators.pattern("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}"),
        Validators.maxLength(25)
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
    if(this.sub!=null)
      this.sub.unsubscribe();
  }

  isControlInvalid(controlName: string): boolean {
    const control = this.signInForm.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  signInClick() {

    this.sub = this.userService.getUserByEmailAndPassword(this.signInForm.controls['email'].value,this.signInForm.controls['password'].value).subscribe(user => {

      if(user!==null){
        localStorage.setItem("idCurrUser",user.id.toString());
        localStorage.setItem('currRole',user.role);
        this.router.navigate(['/homePage']);

      }else{
        this.errorMassage='Incorrect data. Not found user with these email and password. Recheck entered data';
      }
      console.log(Number(localStorage.getItem("idCurrUser")));

    },response => {
      this.errors=response.error;
      console.log(this.errors);
    });
  }
}
