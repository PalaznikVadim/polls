import {Component, OnDestroy, OnInit} from '@angular/core';
import {QuestionModel} from "../../models/question.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {QuestionService} from "../../../services/question.service";
import {AnswerService} from "../../../services/answer.service";
import {TypesService} from "../../../services/types.service";
import {UserAnswerModel} from "../../models/user-answer.model";
import {UserAnswerService} from "../../../services/user-answer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-poll',
  templateUrl: './poll.component.html',
  styleUrls: ['./poll.component.css']
})
export class PollComponent implements OnInit,OnDestroy
{

  quests: QuestionModel[];
  userAnswers:UserAnswerModel[];
  userAnswer: UserAnswerModel;
  subs: any[];
  countSubs = 0;
  errorMessage: string;

  constructor(private formBuilder: FormBuilder, private questionService: QuestionService, private answerService: AnswerService, private typesService: TypesService, private userAnswerService: UserAnswerService, private router: Router) {
  }

  ngOnInit() {
    this.subs = [];
    this.userAnswers=[];
    // this.subs[this.countSubs++] = this.typesService.getAllTypes().subscribe(types => {
    //   this.typesService.types = types;

      this.questionService.getAllQuestionByPollId(Number(localStorage.getItem('idCurrPoll'))).subscribe(quest => {
        this.quests = quest;

        for (let i = 0; i < this.quests.length; i++) {
          if ((this.quests[i].type) == 'radio') {
            this.quests[i].user_answers = [null];
          } else {
            this.quests[i].user_answers = [];
            for (let j = 0; j < this.quests[i].answers.length; j++) {
              this.quests[i].user_answers[j] = false;
            }
          }
        }
    });
  }

  submit() {
    if (this.checkRequiredAnswers()) {
      console.log(this.quests)
      for (let i = 0; i < this.quests.length; i++) {
        this.handleAnswer(this.quests[i]);
      }
    this.userAnswerService.saveAllUserAnswer(this.userAnswers).subscribe(event=>{
      console.log(this.userAnswers);
      this.router.navigate(['/lastPagePoll']);
    });



    }
  }

  checkRequiredAnswers(): boolean {
    for (let i = 0; i < this.quests.length; i++) {
      if (this.quests[i].required == 'Yes') {
        if (this.quests[i].type == 'radio') {
          if (this.quests[i].user_answers[0] != null) {
            continue;
          } else {
            this.errorMessage = 'Question# ' + (i + 1) + ' is mandatory question!';
            return false;
          }
        } else {
          if (this.checkCheckbox(this.quests[i].user_answers as boolean[])) {
            continue;
          } else {
            this.errorMessage = 'Question# ' + (i + 1) + ' is mandatory question!';
            return false;
          }
        }
      }
    }
    this.errorMessage = null;
    return true;
  }


  checkCheckbox(list: boolean[]): boolean {
    for (let j = 0; j < list.length; j++) {
      if (list[j])
        return true;
    }
    return false;
  }


  handleAnswer(quest: QuestionModel): void {
    if (quest.type == 'radio' && quest.user_answers[0] != null) {
      this.userAnswer = new UserAnswerModel();
      this.userAnswer.idAnswer = Number(quest.user_answers[0]);
      this.userAnswer.idQuestion = quest.id;
      this.userAnswers.push(this.userAnswer);
    } else if (quest.type == 'checkbox') {
      for (let i = 0; i < (quest.user_answers as boolean[]).length; i++) {
        if (quest.user_answers[i]) {
          this.userAnswer = new UserAnswerModel();
          this.userAnswer.idAnswer = quest.answers[i].id;
          this.userAnswer.idQuestion = quest.id;
          this.userAnswers.push(this.userAnswer);
        }
      }
    }
  }

  ngOnDestroy(): void {
    if (this.subs != null) {
      for (let i = 0; i < this.subs.length; i++) {
        //this.subs[i].unsubscribe();
      }

    }

  }
}
