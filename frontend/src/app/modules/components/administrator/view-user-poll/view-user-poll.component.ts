import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserModel} from "../../../models/user.model";
import {QuestionService} from "../../../../services/question.service";
import {AnswerService} from "../../../../services/answer.service";
import {QuestionModel} from "../../../models/question.model";
import {TypesService} from "../../../../services/types.service";
import {PollModel} from "../../../models/poll.model";
import {PollService} from "../../../../services/poll.service";

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

  constructor(private pollService:PollService, private questionService:QuestionService, private answerService:AnswerService ,private typeService:TypesService) { }

  ngOnInit() {
    this.subs=[];
    this.tabs=[true,false];
    this.subs[this.countSubs++]=this.pollService.getPollById(Number(localStorage.getItem('idCurrPoll'))).subscribe(poll=>{
      this.poll=poll;
      this.subs[this.countSubs++]=this.questionService.getAllQuestionByPollId(this.poll.id).subscribe(quests=>{
        this.poll.questions=quests;
        for(let i=0;i<quests.length;i++) {
          this.subs[this.countSubs++] = this.answerService.getAllAnswerByIdQuestion(quests[i].id).subscribe(answers=>{
            this.poll.questions[i].answers=answers;
          })
        }
      });
    });
  }

  changeContent(index:number):void{
    for(let i=0;i<this.tabs.length;i++)
      this.tabs[i]=false;
    this.tabs[index]=true;
  }

  ngOnDestroy(): void {
    if(this.subs!=null){
      this.subs[this.countSubs++]=this.pollService.savePoll(this.poll).subscribe();
      for(let i=0;i<this.subs.length;i++)
        this.subs[i].unsubscribe();
    }
  }
}
