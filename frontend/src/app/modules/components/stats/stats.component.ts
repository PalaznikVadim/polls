import { Component, OnInit } from '@angular/core';
import {PollModel} from "../../models/poll.model";
import {QuestionModel} from "../../models/question.model";
import {QuestionService} from "../../../services/question.service";
import {AnswerService} from "../../../services/answer.service";
import {UserAnswerService} from "../../../services/user-answer.service";

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {

  questions:QuestionModel[];
  subs:any[];
  countSubs=0;


  constructor(private questionService:QuestionService,private answerService:AnswerService,private userAnswerService:UserAnswerService) { }

  ngOnInit() {
    this.subs=[];
    this.subs[this.countSubs++]=this.questionService.getAllQuestionByPollId(Number(localStorage.getItem('idCurrPoll'))).subscribe(quests=> {
      this.questions = quests;
      for (let i = 0; i < this.questions.length; i++) {
        this.questions[i].countUserAnswers=0;
        this.subs[this.countSubs++] = this.answerService.getAllAnswerByIdQuestion(this.questions[i].id).subscribe(answers => {
          this.questions[i].answers = answers;
          for (let j = 0; j < answers.length; j++) {
            this.subs[this.countSubs++] = this.userAnswerService.countSelected(answers[j].id).subscribe(count => {
              this.questions[i].countUserAnswers+=count;
              this.questions[i].answers[j].countSelected = count;
            });
          }
        });
      }
      console.log(this.questions);
    });
  }

  percent(i:number):string {
    return (i * 100).toFixed(2);
  }
}
