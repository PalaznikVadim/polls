import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserModel} from "../../models/user.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../services/user.service";
import {Router} from "@angular/router";
import {ErrorModel} from "../../models/error.model";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit,OnDestroy {

  user = new UserModel();
  registrationForm: FormGroup;
  errors:ErrorModel[]=[];
  sub:any;


  constructor(private  userService: UserService,private router: Router) {
  }

  ngOnInit() {
    this.registrationForm = new FormGroup({
      name: new FormControl("", [
        Validators.required,
        Validators.pattern("[A-Za-z]{2,20}"),
      ]),
      surname: new FormControl('', [
        Validators.required,
        Validators.pattern("[A-Za-z-]{2,20}")
      ]),
      DOB: new FormControl("", [
        Validators.required,
      ]),
      email: new FormControl("", [
        Validators.required,
        Validators.maxLength(25),
        Validators.pattern("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}")
      ]),
      passwords: new FormGroup({
          password: new FormControl('', [
            Validators.required,
            Validators.minLength(5),
            Validators.maxLength(25)
          ]),
          confirmPassword: new FormControl('', [
            Validators.required,
            Validators.minLength(5),
            Validators.maxLength(25)
          ])
        }
      ),
    })
  }

  isControlInvalid(controlName: string, groupName?: string): boolean {
    const control = groupName ? this.registrationForm.controls[groupName].controls[controlName] : this.registrationForm.controls[controlName];
    return control.invalid && control.touched;
  }

  createUser() {
      this.user.name=this.registrationForm.controls['name'].value,
      this.user.surname=this.registrationForm.controls['surname'].value,
      this.user.dateOfBirth=this.registrationForm.controls['DOB'].value,
      this.user.email=this.registrationForm.controls['email'].value,
      this.user.password=this.registrationForm.controls['passwords'].controls['password'].value,
      this.user.role='user';

    console.log(this.user);
    this.sub=this.userService.saveUser(this.user).subscribe((data:UserModel) => {
        this.user = data as UserModel;
        if(this.user!==null){
          console.log(this.user);
          this.router.navigate(['/']);
        }
      },response => {
      if(this.errors!=null)
        this.errors=[];
      let resError;
        for(let i=0;i<response.error.length;i++){
          resError=new ErrorModel();
          resError.field=(response as HttpErrorResponse).error[i].field;
          resError.defaultMessage=(response as HttpErrorResponse).error[i].defaultMessage;
          this.errors.push(resError);
        }
      }
    );
    console.log(this.registrationForm);
  }

  checkErrorsForField(field:string):boolean{
    for(let i=0;i<this.errors.length;i++) {
      if (this.errors[i].field == field)
        return true;
    }
    return false
  }

  outErrorsForField(fieldName:string):string[]{
    let errors:string[]=[];
    for(let i=0;i<this.errors.length;i++){
      if(this.errors[i].field==fieldName)
        errors.push(this.errors[i].defaultMessage);
    }
    return errors;
  }

  ngOnDestroy(): void {
    if(this.sub!=null)
      this.sub.unsubscribe();
  }
}
