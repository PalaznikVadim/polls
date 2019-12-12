import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../services/user.service";
import {Router} from "@angular/router";
import {ErrorModel} from "../../models/error.model";
import {ErrorService} from "../../../services/error.service";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit, OnDestroy {

  errorMassage: string;
  sub: any;
  signInForm: FormGroup;
  // errors: Map<string, Array<string>>;
  errors: ErrorModel[];

  constructor(private  userService: UserService, private router: Router,private errorService:ErrorService) {
  }

  ngOnInit() {
    if (localStorage.getItem('err')) {
      this.errorMassage = localStorage.getItem('err');
      localStorage.removeItem('err');
    }

    this.errors = [];
    //this.errors = new Map<string, Array<string>>();
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
      rememberMe: new FormControl()
    });
    if(localStorage.getItem('rememberMe')){
      const data=JSON.parse(localStorage.getItem('rememberMe'));
      this.signInForm.controls['email'].setValue(data.email);
      this.signInForm.controls['password'].setValue(data.password);
      console.log(data);
    }
  }

  ngOnDestroy(): void {
    if (this.sub != null)
      this.sub.unsubscribe();
  }

  isControlInvalid(controlName: string): boolean {
    const control = this.signInForm.controls[controlName];
    const result = control.invalid && control.touched;
    return result;
  }

  rememberMe():void{
    const user={email:this.signInForm.controls['email'].value,
                password:this.signInForm.controls['password'].value};
    if(this.signInForm.controls['rememberMe'].value){
      localStorage.setItem('rememberMe',JSON.stringify(user));
    }
  }

  signInClick() {
    this.sub = this.userService.getUserByEmailAndPassword(this.signInForm.controls['email'].value,
      this.signInForm.controls['password'].value).subscribe(response => {
      if (response.user !== null) {
        this.rememberMe();
        localStorage.setItem("token", response.token);
        this.userService.currUser = response.user;
        this.router.navigate(['/home']);

      } else {
        this.errorMassage = 'Incorrect data. Not found user with these email and password. Recheck entered data';
      }
    }, errorResponse => {
        this.errors=[];

      if (errorResponse.status == '403') {
        console.log('403');
        this.errorMassage = 'Incorrect data. Not found user with these email and password. Recheck entered data';
      } else if (errorResponse.status == '400' && errorResponse.error) {
        const errors = errorResponse.error;
        for (let key in errors) {
          for (let i = 0; i < errors[key].length; i++) {
            const errorResponse = new ErrorModel();
            errorResponse.field = key;
            errorResponse.defaultMessage = errors[key][i];
            this.errors.push(errorResponse);
          }
        }
      }
    });
  }
}
