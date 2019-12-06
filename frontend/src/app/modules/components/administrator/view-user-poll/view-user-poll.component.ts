import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {QuestionService} from "../../../../services/question.service";
import {PollModel} from "../../../models/poll.model";
import {PollService} from "../../../../services/poll.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";

@Component({
  selector: 'app-view-user-poll',
  templateUrl: './view-user-poll.component.html',
  styleUrls: ['./view-user-poll.component.css']
})
export class ViewUserPollComponent implements OnInit,OnDestroy {

  tabs:boolean[];
  subs:any[];
  countSubs=0;
  poll:PollModel;

  modalRef: BsModalRef;
  config = {
    animated: true
  };

  constructor(private modalService: BsModalService, private pollService:PollService, private questionService:QuestionService) { }

  ngOnInit() {

    this.subs=[];
    this.tabs=[false,false];
    this.tabs[Number(localStorage.getItem('index'))]=true;
    this.subs[this.countSubs++]=this.pollService.getPollById(this.pollService.currPoll.id.toString()).subscribe(poll=> {
      this.poll=poll;
      this.subs[this.countSubs++] = this.questionService.getAllQuestionByPollId(poll.id).subscribe(questions => {
        this.poll.questions=questions;
      });
    });
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  changeContent(index:number):void{
    for(let i=0;i<this.tabs.length;i++)
      this.tabs[i]=false;
    this.tabs[index]=true;
  }

  confirm():void{
    this.subs[this.countSubs++]=this.subs[this.countSubs++]=this.pollService.savePoll(this.poll).subscribe(poll=>{
      this.poll.status=poll.status;
      this.modalRef.hide();
    });
  }

  ngOnDestroy(): void {
    if(this.subs!=null){
      for(let i=0;i<this.subs.length;i++)
        this.subs[i].unsubscribe();
    }
  }
}
