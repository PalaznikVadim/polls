import { Component, OnInit } from '@angular/core';
import {QuestionService} from "../../../services/question.service";
import {QuestionWithAnswerCountModel} from "../../models/question-with-answer-count.model";

@Component({
  selector: 'app-table-questions-with-answer-count',
  templateUrl: './table-questions-with-answer-count.component.html',
  styleUrls: ['./table-questions-with-answer-count.component.css']
})
export class TableQuestionsWithAnswerCountComponent implements OnInit {

  questions:QuestionWithAnswerCountModel[];
  subs:any[];

  constructor(private questionService:QuestionService) { }

  ngOnInit() {
    this.subs=[];
    this.subs[this.subs.length]=this.questionService.getQuestionWithAnswerCount().subscribe(questions=>{
      console.log(questions);
      this.questions=questions;

    })
  }

}
