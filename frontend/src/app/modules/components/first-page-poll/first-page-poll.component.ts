import { Component, OnInit } from '@angular/core';
import {PollModel} from "../../models/poll.model";
import {PollService} from "../../../services/poll.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-first-page-poll',
  templateUrl: './first-page-poll.component.html',
  styleUrls: ['./first-page-poll.component.css']
})
export class FirstPagePollComponent implements OnInit {

  pollLink:string;
  errorMessage:string;
  subs:any[];
  countSubs=0;

  constructor(private pollService:PollService, private route:ActivatedRoute) { }

  ngOnInit() {
    this.pollLink = this.route.snapshot.paramMap.get('link');

    this.subs=[];
    this.subs[this.countSubs++]=this.pollService.getPollByLink(this.pollLink).subscribe(poll=>{
      this.pollService.currPoll=poll;
    },
      error => {
        console.log(error);
        this.errorMessage="This link is invalid!";
      });
  }

}
