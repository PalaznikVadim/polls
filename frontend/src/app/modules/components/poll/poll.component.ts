import {Component, OnInit} from '@angular/core';
import {QuestionModel} from "../../models/question.model";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-poll',
  templateUrl: './poll.component.html',
  styleUrls: ['./poll.component.css']
})
export class PollComponent implements OnInit {

  quests: QuestionModel[];

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit() {

    // this.quests = [
    //   {
    //     id:1,
    //     title: "Quest1",
    //     type: "radio",
    //     answers: ['answer1', 'answer2'],
    //     user_answer: [null],
    //     required:true
    //   },
    //   {
    //     id:2,
    //     title: "Quest2",
    //     type: "checkbox",
    //     answers: ['answer1', 'answer2', 'answwer3vvvvvvvvvvv'],
    //     user_answer: [false,false,false],
    //     required:null
    //   },
    //   {
    //     id:2,
    //     title:"quest 4",
    //     type:"radio" ,
    //     answers:['answer 2','sfdfdsfsdf',"gfdfdfgdg"],
    //     user_answer:[null],
    //     required:true
    //   }];


  }

  submit() {
    console.log(this.quests);

  }


}
