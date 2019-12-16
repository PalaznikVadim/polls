import { Component, OnInit } from '@angular/core';
import {QuestionModel} from "../../models/question.model";
import {StatsService} from "../../../services/stats.service";
import {PollService} from "../../../services/poll.service";

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {

  questions: QuestionModel[]=[];
  subs: any[];
  countSubs = 0;


  constructor(private stats: StatsService,private pollService:PollService) {
  }

  ngOnInit() {
    this.subs = [];
    console.log(this.pollService.currPoll);
    this.subs[this.countSubs++] = this.stats.getPollStats(this.pollService.currPoll.id).subscribe(stats => {
      this.questions = stats;
      console.log(this.questions);
    });
  }
}
