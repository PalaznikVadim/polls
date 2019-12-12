import { Component, OnInit } from '@angular/core';
import {UserService} from "../../../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-top-nav',
  templateUrl: './top-nav.component.html',
  styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {

  constructor(private userService:UserService,private router:Router) { }
  private isCreatePage:boolean=false;
  private isLinkPage:boolean=false;
  private isStatsPage:boolean=false;
  ngOnInit() {
    if(this.router.url=='/creation_poll'||this.router.url=='/viewUserPoll'){
      this.isCreatePage=true;
    }else if(this.router.url=='/pollLink'){
      this.isLinkPage=true;
    }else {
      this.isStatsPage=true;
    }
  }

  back() {
    if(!this.userService.idSelectedUser){
      this.router.navigate(['home']);
    } else {
      this.router.navigate(['userPolls']);
    }
  }

  goToQuestions() {
    if(this.userService.idSelectedUser&&this.userService.currUser.role=='admin') {
      this.router.navigate(['viewUserPoll']);
    }else{
      this.router.navigate(['creation_poll']);
    }
  }

  exit() {
    localStorage.removeItem('token');
    this.router.navigate(['/']);
  }
}
