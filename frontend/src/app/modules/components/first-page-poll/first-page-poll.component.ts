import { Component, OnInit } from '@angular/core';
import {PollModel} from "../../models/poll.model";

@Component({
  selector: 'app-first-page-poll',
  templateUrl: './first-page-poll.component.html',
  styleUrls: ['./first-page-poll.component.css']
})
export class FirstPagePollComponent implements OnInit {

  poll:PollModel;

  constructor() { }

  ngOnInit() {
    this.poll=new PollModel();
    this.poll={
      id: 0,
      title:"poll1",
      theme:"theme1",
      description:"dfssfdsfdsfsdfdsfsdfsdf fsdfdsfdg fgdgfdgd ",
      dateAdding:new Date(),
      quests:[{
        id:1,
        title:"quest1",
        type:"radio",
        answers:["answer1","answer2"],
        user_answer:null,
        required:true
      }],

    }
  }

}
