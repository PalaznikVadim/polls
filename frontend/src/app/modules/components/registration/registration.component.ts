import {Component, OnInit} from '@angular/core';
import {UserModel} from "../../models/user.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../services/user.service";
import {rootRoute} from "@angular/router/src/router_module";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user = new UserModel();
  registrationForm: FormGroup;


  constructor(private  userService: UserService,private router: Router) {
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
        }
      ),
    })
  }

  isControlInvalid(controlName: string, groupName?: string): boolean {
    const control = groupName ? this.registrationForm.controls[groupName].controls[controlName] : this.registrationForm.controls[controlName];
    return control.invalid && control.touched;
  }

  createUser() {
    this.user={
      id: null,
      name:this.registrationForm.controls['name'].value,
      surname:this.registrationForm.controls['surname'].value,
      dateOfBirth:this.registrationForm.controls['DOB'].value,
      email:this.registrationForm.controls['email'].value,
      password:this.registrationForm.controls['passwords'].controls['password'].value,
      role:'user',
      polls: null,
    }
    console.log(this.user);
    this.userService.saveUser(this.user).subscribe(user => {

        this.user = user as UserModel;
        if(this.user!==null){
          console.log(user);
          this.router.navigate(['/']);
        }
      }
    )
    console.log(this.registrationForm);
  }
}
