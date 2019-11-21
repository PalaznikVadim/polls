import { Component, OnInit } from '@angular/core';
import {PollModel} from "../../../models/poll.model";
import {QuestionService} from "../../../../services/question.service";
import {PollService} from "../../../../services/poll.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  polls:PollModel[];
  subs:any;
  countSubs=0;

  constructor(private pollService:PollService,private questionService:QuestionService,private router:Router) { }

  ngOnInit() {
    this.pollService.currPoll=new PollModel();
    this.polls=[];
    this.subs=[];
    this.subs[this.countSubs++]=this.pollService.getPollsByUserId(Number(localStorage.getItem('idSelectedUser'))).subscribe(polls=>{
      this.polls=polls;
      console.log(this.polls);
    })

  }

  goToPollQuestion(id: number) {
    this.subs[this.countSubs++]=this.pollService.getPollById(id.toString()).subscribe(poll=>{
      this.pollService.currPoll=poll;
      localStorage.setItem('index',(0).toString());
      this.router.navigate(['viewUserPoll']);
    });
  }

  goToStat(id: number) {
    this.subs[this.countSubs++]=this.pollService.getPollById(id.toString()).subscribe(poll=>{
      this.pollService.currPoll=poll;
      localStorage.setItem('index',(1).toString());
      this.router.navigate(['viewUserPoll']);
    });
  }
}
