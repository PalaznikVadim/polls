import { Component, OnInit } from '@angular/core';
import {PollModel} from "../../models/poll.model";

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {

  poll:PollModel;

  constructor() { }

  ngOnInit() {
    this.poll=new PollModel();
    // this.poll={
    //   id: 0,
    //   name:"poll1",
    //   idTheme:null,
    //   description:"dfssfdsfdsfsdfdsfsdfsdf fsdfdsfdg fgdgfdgd ",
    //   date:new Date(),
    //   quests:[{
    //     id:1,
    //     title: "Quest1",
    //     type: "radio",
    //     answers: ['answer1', 'answer2'],
    //     user_answer: [null],
    //     required:true
    //   },
    //     {
    //       id:2,
    //       title: "Quest2",
    //       type: "checkbox",
    //       answers: ['answer1', 'answer2', 'answwer3vvvvvvgffgvvv'],
    //       user_answer: [false,false,false],
    //       required:false
    //     },
    //     {
    //       id:2,
    //       title:"quest 4",
    //       type:"radio" ,
    //       answers:['answer 2','sfdfdsfsdf',"gfdfdfgdg"],
    //       user_answer: [null],
    //       required:true
    //     }],
    // }
  }

}
