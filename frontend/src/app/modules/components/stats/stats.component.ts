import { Component, OnInit } from '@angular/core';
import {PollModel} from "../../models/poll.model";
import {QuestionModel} from "../../models/question.model";
import {QuestionService} from "../../../services/question.service";
import {AnswerService} from "../../../services/answer.service";
import {UserAnswerService} from "../../../services/user-answer.service";
import {StatsService} from "../../../services/stats.service";

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {

  questions: QuestionModel[];
  subs: any[];
  countSubs = 0;


  constructor(private stats: StatsService) {
  }

  ngOnInit() {
    this.subs = [];
    this.subs[this.countSubs++] = this.stats.getPollStats(Number(localStorage.getItem('idCurrPoll'))).subscribe(stats => {
      this.questions = stats;
      console.log(this.questions);
    });
  }
}
