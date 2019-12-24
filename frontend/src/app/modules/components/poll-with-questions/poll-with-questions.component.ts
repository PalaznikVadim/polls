import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ErrorModel} from "../../models/error.model";
import {QuestionModel} from "../../models/question.model";
import {AnswerModel} from "../../models/answer.model";
import {QuestionService} from "../../../services/question.service";
import {PollService} from "../../../services/poll.service";
import {AnswerService} from "../../../services/answer.service";
import {UserService} from "../../../services/user.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorService} from "../../../services/error.service";

@Component({
  selector: 'app-poll-with-questions',
  templateUrl: './poll-with-questions.component.html',
  styleUrls: ['./poll-with-questions.component.css']
})
export class PollWithQuestionsComponent implements OnInit, OnDestroy {

  modalRef: BsModalRef;
  config = {
    animated: true
  };
  errors: ErrorModel[] = [];
  isNew: boolean;
  subs: any[];
  countSub = 0;
  currRole: string;
  quest: QuestionModel;
  answer: AnswerModel;

  idCurrQuest: number;
  indexCurQuest: number;
  role: string;


  constructor(private modalService: BsModalService, private questionService: QuestionService, private errorService: ErrorService,
              private pollService: PollService, private answerService: AnswerService, private userService: UserService) {
  }

  ngOnInit() {
    console.log('ngOnInit()');
    this.currRole = this.userService.currUser.role;
    this.quest = new QuestionModel();
    this.quest.answers = [];
    this.subs = [];
    this.subs[this.countSub++] = this.questionService.getAllQuestionByPollId(this.pollService.currPoll.id)
      .subscribe(questions => {
        this.pollService.currPoll.questions = questions as QuestionModel[];
      });
  }


  addQuest(template: TemplateRef<any>) {
    this.isNew = true;
    this.quest = new QuestionModel();
    this.quest.required = 'No';
    this.quest.type = 'radio';
    this.quest.idPoll = this.pollService.currPoll.id;
    this.quest.answers = [] as AnswerModel[];
    for (let i = 0; i < 3; i++) {
      this.answer = new AnswerModel();
      this.quest.answers.push(this.answer);
    }

    console.log(this.quest);

    this.errors = [];
    this.modalRef = this.modalService.show(template);
  }

  createQuestion() {
    console.log(this.quest.type);
    this.questionService.saveQuestion(this.quest).subscribe(quest => {
        if (quest != null) {
          this.pollService.currPoll.questions.push(quest);
        }
        this.modalRef.hide()
      }, response => {
        console.log(response);
        this.parseErrorResponse(response);
      }
    );
  }

  editQuest(question: QuestionModel, template) {

    this.isNew = false;
    this.quest = question;
    this.modalRef = this.modalService.show(template);
  }

  deleteQuest(id: number, i: number) {
    this.questionService.deleteQuestion(id).subscribe(event => {
      this.pollService.currPoll.questions.splice(i, 1);
      this.modalRef.hide();
    });
  }

  addNewAnswer() {
    this.answer = new AnswerModel();
    this.quest.answers.push(this.answer);
  }

  deleteAnswer(index: number) {
    if (this.quest.answers[index].id != null) {
      this.answerService.deleteById(this.quest.answers[index].id).subscribe(event => {
        this.quest.answers.splice(index, 1);
      });
    } else {
      this.quest.answers.splice(index, 1);
    }

  }

  openModal(template: TemplateRef<any>, id?: number, i?: number) {
    this.idCurrQuest = id;
    this.indexCurQuest = i;
    this.modalRef = this.modalService.show(template, {class: 'modal-sm'});
  }

  ngOnDestroy(): void {
    for (let i = 0; i < this.subs.length; i++) {
      this.subs[i].unsubscribe();
    }
  }

  updateQuestion() {
    this.subs[this.countSub++] = this.questionService.saveQuestion(this.quest).subscribe(quest => {
        console.log(this.quest.type);
        if (quest != null) {
          this.quest = quest as QuestionModel;
          console.log(this.quest);
        }
        this.modalRef.hide();
      }, response => {
        console.log(response);
        this.parseErrorResponse(response);
      }
    );
  }

  parseErrorResponse(response: HttpErrorResponse) {
    if (this.errors.length != 0) {
      this.errors = [];
    }
    let resError;
    for (let i = 0; i < response.error.length; i++) {
      resError = new ErrorModel();
      if (response.error[i].objectName != 'withoutAnswer') {
        resError.field = response.error[i].field;
      } else {
        resError.field = response.error[i].objectName;
      }
      resError.defaultMessage = response.error[i].defaultMessage;
      resError.code = response.error[i].code;
      resError.rejectedValue = response.error[i].rejectedValue;
      this.errors.push(resError);
    }
    console.log(this.errors);
  }

  generateLink() {
    const link = this.pollService.currPoll.link;
    this.pollService.submitPoll(this.pollService.currPoll.id).subscribe(poll => {
      let quests = this.pollService.currPoll.questions;
      this.pollService.currPoll = poll;
      this.pollService.currPoll.questions = quests;
      if (link) {
        this.modalRef.hide();
      }
    });
  }

  submit(templateSubmitConfirm: TemplateRef<any>) {
    if (this.pollService.currPoll.link) {
      this.modalRef = this.modalService.show(templateSubmitConfirm);
    } else {
      this.generateLink();
    }

  }

  checkErrorsForAnswerField(field: string, code: number, rejectedValue: string): boolean {
    if (!this.isNew) {
      for (let i = 0; i < this.errors.length; i++) {
        if (this.errors[i].field == field && this.errors[i].code == code)
          return true;
      }
      return false
    } else {
      for (let i = 0; i < this.errors.length; i++) {
        if (this.errors[i].field == field && this.errors[i].rejectedValue == rejectedValue)
          return true;
      }
      return false
    }
  }

  outErrorsForAnswerField(fieldName: string, code: number, rejectedValue: string): string[] {
    let errors: string[] = [];
    if (!this.isNew) {
      for (let i = 0; i < this.errors.length; i++) {
        if (this.errors[i].field == fieldName && this.errors[i].rejectedValue == rejectedValue) {
          errors.push(this.errors[i].defaultMessage);
        }
      }
    } else {
      for (let i = 0; i < this.errors.length; i++) {
        if (this.errors[i].field == fieldName && this.errors[i].rejectedValue == rejectedValue) {
          errors.push(this.errors[i].defaultMessage);
        }
      }
    }
    return errors;
  }

  isValidQuest(): boolean {
    if (!this.quest.textTitle||!this.quest.textTitle.trim().length){
      return false;
    }
    for (let i=0;i<this.quest.answers.length;i++) {
      if (!this.quest.answers[i].text||!this.quest.answers[i].text.trim().length) {
        return false;
      }
    }
    return true;
  }

  confirm() {
    this.subs[this.subs.length] = this.pollService.savePoll(this.pollService.currPoll).subscribe(poll => {
      this.pollService.currPoll.status = poll.status;
      this.modalRef.hide();
    });
  }
}
