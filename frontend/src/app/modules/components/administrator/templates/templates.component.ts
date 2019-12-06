import {Component, OnInit, TemplateRef} from '@angular/core';
import {PollService} from "../../../../services/poll.service";
import {PollModel} from "../../../models/poll.model";
import {BsModalRef, BsModalService, PageChangedEvent} from "ngx-bootstrap";
import {Router} from "@angular/router";
import {RestPageModel} from "../../../models/rest-page.model";
import {map, tap} from "rxjs/operators";
import {ThemeService} from "../../../../services/theme.service";
import {UserService} from "../../../../services/user.service";

@Component({
  selector: 'app-templates',
  templateUrl: './templates.component.html',
  styleUrls: ['./templates.component.css']
})
export class TemplatesComponent implements OnInit {

  polls:PollModel[];
  subs:any[];
  countSubs=0;
  indexCurrPoll:number;
  idCurrPoll:number;
  page:RestPageModel;
  size:number;
  currentPage: number;
  sort:string;
  order:string;
  search: string;
  private searchResult: string;
  private select: string;


  constructor(private pollService:PollService,private modalService: BsModalService,private router:Router,
              private themeService:ThemeService,private userService:UserService) { }

  ngOnInit() {
    this.search='';
    this.select='all';
    this.order='ASC';
    this.sort='name';
    this.currentPage=1;
    this.size=6;
    this.polls=[];
    this.subs=[];
    this.getPolls(this.currentPage);
    this.subs[this.subs.length] = this.themeService.getUserPollThemes(this.userService.currUser.id).subscribe(themes => {
      this.themes = themes;
      console.log(this.themes)
    });
  }

  modalRef: BsModalRef;
  config = {
    animated: true
  };
  themes: string[];

  newTemplate() {
    this.pollService.currPoll=null;
    this.router.navigate(['titleNewPoll']);
  }

  editClick(poll:PollModel) {
    localStorage.setItem('index',(0).toString());
    this.pollService.currPoll=poll;
    this.router.navigate(['/constructorPoll']);
  }

  delete(idCurrPoll: any, indexCurrPoll: any) {
    this.pollService.deletePoll(idCurrPoll.toString()).subscribe(value=>{
      this.polls.splice(indexCurrPoll,1);
      this.modalRef.hide();
      console.log("Poll with id="+idCurrPoll+"deleted");
    });
  }

  confirm(templateConfirm: TemplateRef<any>, id: number, i: number) {
    this.idCurrPoll=id;
    this.indexCurrPoll=i;
    this.modalRef = this.modalService.show(templateConfirm);
  }

  pageChanged(event: PageChangedEvent) {
    this.currentPage=event.page;
    console.log(this.currentPage);
    this.getPolls(this.currentPage);
  }

  getPolls(page: number) {
    this.currentPage = page;
    this.subs[this.countSubs++] = this.pollService.getPollsPageByUserId(this.userService.currUser.id,
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
}
