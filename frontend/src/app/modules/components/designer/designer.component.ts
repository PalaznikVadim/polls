import {Component, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {PollModel} from "../../models/poll.model";
import {QuestionModel} from "../../models/question.model";






@Component({
  selector: 'app-designer',
  templateUrl: './designer.component.html',
  styleUrls: ['./designer.component.css']
})
export class DesignerComponent implements OnInit {

  modalRef: BsModalRef;
  config = {
    animated: true
  };

  poll=new PollModel();
  quest: QuestionModel;

  constructor(private modalService: BsModalService) { }

  ngOnInit() {
    this.poll.quests=[];
  }


  addQuest(template: TemplateRef<any>){
    this.quest=new QuestionModel();
    this.quest.title='Enter question title';
    this.quest.type='Select one answer';
    this.quest.answers=["Answer1","Answer2","Answer3"]
    this.modalRef = this.modalService.show(template);
  }

  addNewAnswer() {
    this.quest.answers.push("Answer");
  }

  deleteAnswer(index:number) {
    this.quest.answers.splice(index,1);
  }

  createQuestion() {
    this.poll.quests.push(this.quest);
    console.log(this.poll);
    this.modalRef.hide()
  }

  deleteQuest(i: number) {
    this.poll.quests.splice(i,1);
  }

  editQuest(i: number,template) {
    this.quest=this.poll.quests[i];
    this.poll.quests.splice(i,1);
    this.modalRef = this.modalService.show(template);
  }
}
