import { Component, OnInit } from '@angular/core';
import {PollModel} from "../../models/poll.model";
import {PollService} from "../../../services/poll.service";

@Component({
  selector: 'app-first-page-poll',
  templateUrl: './first-page-poll.component.html',
  styleUrls: ['./first-page-poll.component.css']
})
export class FirstPagePollComponent implements OnInit {

  poll:PollModel;
  subs:any[];
  countSubs=0;

  constructor(private pollService:PollService) { }

  ngOnInit() {
    this.subs=[];
    this.subs[this.countSubs++]=this.pollService.getPollById(Number(localStorage.getItem('idCurrPoll'))).subscribe(poll=>{
      this.poll=poll;
    })
  }

}
