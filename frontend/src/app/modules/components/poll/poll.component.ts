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
export class PollComponent implements OnInit,OnDestroy {

  quests: QuestionModel[];
  userAnswer: UserAnswerModel;
  subs: any[];
  countSubs = 0;
  errorMessage: string;

  constructor(private formBuilder: FormBuilder, private questionService: QuestionService, private answerService: AnswerService, private typesService: TypesService, private userAnswerService: UserAnswerService, private router: Router) {
  }

  ngOnInit() {
    this.subs = [];
    this.subs[this.countSubs++] = this.typesService.getAllTypes().subscribe(types => {
      this.typesService.types = types;

      this.questionService.getAllQuestionByPollId(Number(localStorage.getItem('idCurrPoll'))).subscribe(quest => {
        this.quests = quest;

        for (let i = 0; i < this.quests.length; i++) {

          this.subs[this.countSubs++] = this.answerService.getAllAnswerByIdQuestion(this.quests[i].id).subscribe(answers => {
            this.quests[i].answers = answers;

            if (this.typesService.findTypeById(this.quests[i].idType) == 'radio') {

            } else {
              this.quests[i].user_answers = [];
              for (let j = 0; j < answers.length; j++) {
                this.quests[i].user_answers[j] = false;
              }
            }
          });
        }
        console.log(this.quests)
      });
    });
  }

  submit() {
    if (this.checkRequiredAnswers()) {
      for (let i = 0; i < this.quests.length; i++) {
        this.saveAnswer(this.quests[i]);
      }
      this.router.navigate(['/lastPagePoll']);
    }
  }

  checkRequiredAnswers(): boolean {
    for (let i = 0; i < this.quests.length; i++) {
      if (this.quests[i].required == 'Yes') {
        if (this.typesService.findTypeById(this.quests[i].idType) == 'radio') {
          if (this.quests[i].user_answers != null) {
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

  saveAnswer(quest: QuestionModel): void {
    if (this.typesService.findTypeById(quest.idType) == 'radio' && quest.user_answers != null) {
      this.userAnswer = new UserAnswerModel();
      this.userAnswer.idAnswer = quest.user_answers as number;
      this.userAnswer.idQuestion = quest.id;
      this.subs[this.countSubs++] = this.userAnswerService.saveUserAnswer(this.userAnswer).subscribe(userAnswer => {

      });
    } else if (this.typesService.findTypeById(quest.idType) == 'checkbox') {
      for (let i = 0; i < (quest.user_answers as boolean[]).length; i++) {
        if (quest.user_answers[i]) {
          this.userAnswer = new UserAnswerModel();
          this.userAnswer.idAnswer = quest.answers[i].id;
          this.userAnswer.idQuestion = quest.id;
          this.subs[this.countSubs++] = this.userAnswerService.saveUserAnswer(this.userAnswer).subscribe(userAnswer => {

          });
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
