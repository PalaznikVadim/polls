import { Component, OnInit } from '@angular/core';
import {PollModel} from "../../../models/poll.model";
import {QuestionService} from "../../../../services/question.service";
import {PollService} from "../../../../services/poll.service";
import {Router} from "@angular/router";
import {RestPageModel} from "../../../models/rest-page.model";
import {PageChangedEvent} from "ngx-bootstrap";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  polls:PollModel[];
  subs:any;
  countSubs=0;
  sort:string='name';
  order:string='desc';
  search: string;
  page: RestPageModel;
  size: number=6;
  prevSize:number=6;
  currentPage: number = 1;
  private searchResult: string;
  private prevSort: string='name';

  constructor(private pollService:PollService,private questionService:QuestionService,private router:Router) { }

  ngOnInit() {
    this.pollService.currPoll=new PollModel();
    this.polls=[];
    this.subs=[];
    this.getPolls();

  }

  goToPollQuestion(id: number) {
    this.subs[this.countSubs++]=this.pollService.getPollById(id.toString()).subscribe(poll=>{
      this.pollService.currPoll=poll;
      localStorage.setItem('idCurrPoll',(poll.id).toString());
      localStorage.setItem('index',(0).toString());
      this.router.navigate(['viewUserPoll']);
    });
  }

  goToStat(id: number) {
    this.subs[this.countSubs++]=this.pollService.getPollById(id.toString()).subscribe(poll=>{
      this.pollService.currPoll=poll;
      localStorage.setItem('idCurrPoll',(poll.id).toString());
      localStorage.setItem('index',(1).toString());
      this.router.navigate(['viewUserPoll']);
    });
  }

  getPolls(){
    this.subs[this.countSubs++]=this.pollService.getPollsByUserId(Number(localStorage.getItem('idSelectedUser')),this.currentPage-1,this.size,this.sort).subscribe(page=>{
      this.polls=page.content as PollModel[];
      this.page=page;
      console.log(this.polls);
    });
  }

  selectSort() {
    if(this.prevSort!=this.sort) {
      this.prevSort=this.sort;
      this.currentPage=1;
      this.getPolls();
    }
  }

  searchPoll() {
    if(this.search!=''){
      this.currentPage=1;
      this.subs[this.countSubs++]=this.pollService.searchPollsBySubstr(this.search,Number(localStorage.getItem('idSelectedUser')),this.currentPage-1,this.size,this.sort).subscribe(page=>{
        this.page = page as RestPageModel;
        this.polls = this.page.content as PollModel[];
        if(page.totalElements!=0){
          this.searchResult='Found '+page.totalElements+' poll(s):';
        }else{
          this.searchResult='Nothing found!';
        }
      });
    }
  }

  selectSize() {
    if(this.prevSize!=this.size){
      this.currentPage=1;
    }
  }

  pageChanged(event: PageChangedEvent) {
    this.currentPage=event.page;
    console.log(this.currentPage);

  }
}
