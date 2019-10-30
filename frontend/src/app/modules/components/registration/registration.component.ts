import {Component, OnInit} from '@angular/core';
import {UserModel} from "../../models/user.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user = new UserModel();
  registrationForm: FormGroup;


  constructor() {
  }

  ngOnInit() {
    this.registrationForm = new FormGroup({
      name: new FormControl("", [
        Validators.required,
        //Validators.pattern("[A-za-z]"),
        Validators.minLength(2),
        Validators.maxLength(20)
      ]),
      surname: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(20)
      ]),
      DOB: new FormControl("", [
        Validators.required
      ]),
      email: new FormControl("", [
        Validators.required,
        Validators.pattern("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}")
      ]),
      passwords: new FormGroup({
          password: new FormControl('', [
            Validators.required,
            Validators.minLength(5),
            Validators.maxLength(20)
          ]),
          confirmPassword: new FormControl('', [
            Validators.required,
            Validators.minLength(5),
            Validators.maxLength(20)
          ])
        }, {},
        // this.equalValidator
      ),


    })
  }

  equalValidator({value}: FormGroup): { [key: string]: any } {
    const [first, ...rest] = Object.keys(value || {});
    const valid = rest.every(v => value[v] === value[first]);
    return valid ? null : {equal: true};
  }

  isControlInvalid(controlName: string, groupName?: string): boolean {
    const control = groupName ? this.registrationForm.controls[groupName].controls[controlName] : this.registrationForm.controls[controlName];
    return control.invalid && control.touched;
  }

  createUser() {
    console.log(this.registrationForm);
  }
}
