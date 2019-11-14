import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {PollModel} from "../../models/poll.model";
import {QuestionModel} from "../../models/question.model";
import {AnswerModel} from "../../models/answer.model";
import {QuestionService} from "../../../services/question.service";
import {AnswerService} from "../../../services/answer.service";
import {TypesService} from "../../../services/types.service";
import {TypeQuestionModel} from "../../models/type_question.model";


@Component({
  selector: 'app-designer',
  templateUrl: './designer.component.html',
  styleUrls: ['./designer.component.css']
})
export class DesignerComponent implements OnInit,OnDestroy {

  modalRef: BsModalRef;
  config = {
    animated: true
  };

  isNew:boolean;
  subs:any[];
  countSub=0;
  poll:PollModel;
  quest: QuestionModel;
  strTypeQuestion:string;
  //types:TypeQuestionModel[];
  answer:AnswerModel;

  idCurrQuest:number;
  indexCurQuest:number;


  constructor(private modalService: BsModalService,private questionService:QuestionService,private answerService:AnswerService,private typesService:TypesService) { }

  ngOnInit() {
    console.log('ngOnInit()');

    this.quest=new QuestionModel();
    this.quest.answers=[];
    this.subs=[];
    // this.subs[this.countSub++]=this.typesService.getAllTypes().subscribe(types=>{
    //   this.typesService.types=types as TypeQuestionModel[];
    //   this.strTypeQuestion=this.typesService.types[0].description;
    // });
    this.poll=new PollModel();
    this.poll.id=Number(localStorage.getItem('idCurrPoll'));
    this.subs[this.countSub++]=this.questionService.getAllQuestionByPollId(this.poll.id).subscribe(questions => {
      this.poll.questions=questions as QuestionModel[];

      console.log(this.poll);
    });
  }


  addQuest(template: TemplateRef<any>){
console.log(this.strTypeQuestion+'='+this.typesService.findIdTypeByDescription(this.strTypeQuestion));
    this.isNew=true;
    this.quest=new QuestionModel();
    this.quest.textTitle='Enter question title';
    this.quest.required='No';
    this.quest.type='radio';
    this.quest.idPoll=Number(localStorage.getItem('idCurrPoll'));
    this.quest.answers=[] as AnswerModel[];
    for(let i=0;i<3;i++) {
      this.answer = new AnswerModel();
      this.answer.text = 'Answer'+(i+1);
      this.quest.answers.push(this.answer);
    }
    console.log(this.quest.answers);

    this.modalRef = this.modalService.show(template);
  }

  createQuestion() {
    console.log(this.quest);
    //this.quest.type="Select one answer"?'radio':'checkbox';
    this.questionService.saveQuestion(this.quest).subscribe(quest=>{
      if(quest!=null){
        //this.quest=quest as QuestionModel;
        console.log(quest);
        this.poll.questions.push(this.quest);}
      }
    );
    this.modalRef.hide()
  }

  editQuest(question: QuestionModel,template) {

    this.isNew=false;
    this.quest=question;
    this.modalRef = this.modalService.show(template);
  }

  deleteQuest(id:number,i: number) {
    this.questionService.deleteQuestion(id).subscribe(event=>{
      this.poll.questions.splice(i,1);
      this.modalRef.hide();
    });
  }

  addNewAnswer() {
    this.answer=new AnswerModel();
    this.answer.text='Answer';
    this.quest.answers.push(this.answer);
  }

  deleteAnswer(index:number) {
    this.quest.answers.splice(index,1);
    // if(this.quest.answers[index].id!=null){
    //   console.log(this.quest.answers[index].id);
    // this.answerService.deleteById(this.quest.answers[index].id).subscribe(event=>{
    //   this.quest.answers.splice(index,1);
    // });
    // }else{
    //   this.quest.answers.splice(index,1);
    // }

  }

  openModal(template: TemplateRef<any>,id:number,i:number) {
    this.idCurrQuest=id;
    this.indexCurQuest=i;
    this.modalRef = this.modalService.show(template, {class: 'modal-sm'});
  }

  ngOnDestroy(): void {
    for(let i=0;i<this.subs.length;i++){
      this.subs[i].unsubscribe();
    }
  }

  updateQuestion() {
    this.quest.type="Select one answer"?'radio':'checkbox';
    this.subs[this.countSub++]=this.questionService.saveQuestion(this.quest).subscribe(quest => {
      if (quest != null) {
        this.quest = quest as QuestionModel;
        console.log(this.quest);
      }
    });
    this.modalRef.hide();
  }
}
