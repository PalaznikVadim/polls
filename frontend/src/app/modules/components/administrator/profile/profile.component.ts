import { Component, OnInit } from '@angular/core';
import {PollModel} from "../../../models/poll.model";
import {QuestionService} from "../../../../services/question.service";
import {PollService} from "../../../../services/poll.service";
import {Router} from "@angular/router";
import {RestPageModel} from "../../../models/rest-page.model";
import {PageChangedEvent} from "ngx-bootstrap";
import {map, tap} from "rxjs/operators";
import {ThemeService} from "../../../../services/theme.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  polls:PollModel[];
  subs:any;
  sort:string;
  order:string;
  search: string;
  page: RestPageModel;
  size: number;
  currentPage: number;
  private searchResult: string;
  private select: string;
  themes: string[];

  constructor(private pollService:PollService,private questionService:QuestionService,private router:Router,
              private themeService:ThemeService) { }

  ngOnInit() {
    this.currentPage=1;
    this.size=6;
    this.order='ASC';
    this.sort='name';
    this.search='';
    this.select='all';
    this.pollService.currPoll=new PollModel();
    this.subs=[];
    this.getPolls(1);
    this.subs[this.subs.length]=this.themeService.getUserPollThemes(Number(localStorage.getItem('idSelectedUser')))
      .subscribe(themes=>{
      this.themes=themes;
    });

  }

  goToPollQuestion(id: number) {
    this.subs[this.subs.length]=this.pollService.getPollById(id.toString()).subscribe(poll=>{
      this.pollService.currPoll=poll;
      // localStorage.setItem('idCurrPoll',(poll.id).toString());
      localStorage.setItem('index',(0).toString());
      this.router.navigate(['viewUserPoll']);
    });
  }

  goToStat(id: number) {
    this.subs[this.subs.length]=this.pollService.getPollById(id.toString()).subscribe(poll=>{
      this.pollService.currPoll=poll;
      // localStorage.setItem('idCurrPoll',(poll.id).toString());
      localStorage.setItem('index',(1).toString());
      this.router.navigate(['viewUserPoll']);
    });
  }

  getPolls(page: number) {
    this.currentPage = page;
    this.subs[this.subs.length] = this.pollService.getPollsPageByUserId(Number(localStorage.getItem("idSelectedUser")),
      this.select, this.search, this.currentPage - 1, this.size, this.sort, this.order)
      .pipe(
        tap((pageResponse: RestPageModel) => {
          this.page = pageResponse;
          if (this.search)
            this.searchResult = 'Found ' + this.page.totalElements + ' poll(s)';
          else
            this.searchResult=null;
        }),
        map((pageResponse: RestPageModel) => pageResponse.content)
      )
      .subscribe((polls: PollModel[]) => {
        this.polls = polls;
      });
  }

  pageChanged(event: PageChangedEvent) {
    this.currentPage=event.page;
    this.getPolls(this.currentPage);
  }
}
