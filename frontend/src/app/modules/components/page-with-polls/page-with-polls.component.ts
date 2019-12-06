import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {PollModel} from "../../models/poll.model";
import {RestPageModel} from "../../models/rest-page.model";
import {BsModalRef, BsModalService, PageChangedEvent} from "ngx-bootstrap";
import {PollService} from "../../../services/poll.service";
import {Router} from "@angular/router";
import {ThemeService} from "../../../services/theme.service";
import {UserService} from "../../../services/user.service";
import {map, tap} from "rxjs/operators";

@Component({
  selector: 'app-page-with-polls',
  templateUrl: './page-with-polls.component.html',
  styleUrls: ['./page-with-polls.component.css']
})
export class PageWithPollsComponent implements OnInit,OnDestroy {

  private subs: any;
  private indexCurrPoll: number;//?
  private idCurrPoll: number;//?
  private polls: PollModel[];
  private page: RestPageModel;
  private currentPage: number;
  private sort: string;
  private size: number;
  private order: string;
  searchResult: string;
  search: string;
  themes: string[];
  isPoll:boolean;
  idUser:number;


  modalRef: BsModalRef;
  config = {
    animated: true
  };
  select: string;


  constructor(private modalService: BsModalService, private  pollService: PollService, private router: Router, private themeService: ThemeService,private userService:UserService) {
  }

  ngOnInit() {
    this.select = 'all';
    this.search='';
    this.currentPage=1;
    this.size=6;
    this.order='ASC';
    this.sort='name';
    if(this.router.url.includes('myTemplates')){
      this.isPoll=false;
      this.idUser=this.userService.currUser.id;
    }else{
      this.isPoll=true;
      if(this.router.url.includes('userPolls')){
        this.idUser=1;
      }else{
        this.idUser=this.userService.currUser.id;
      }
    }
    console.log(this.router.url.includes('myTemplates'));
    this.subs = [];
    this.getPolls(this.currentPage);
    this.subs[this.subs.length] = this.themeService.getUserPollThemes(this.idUser).subscribe(themes => {
      this.themes = themes;
      console.log(this.themes)
    });
    console.log(this.userService.currUser);
  }

  ngOnDestroy(): void {
    for (let i = 0; i < this.subs.length; i++)
      this.subs[i].unsubscribe();
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  deletePoll(id: number, i: number) {
    this.pollService.deletePoll(id.toString()).subscribe(value => {
      this.polls.splice(i, 1);
      this.getPolls(1)
      this.modalRef.hide();
      console.log("Poll with id=" + id + "deleted");
    });
  }

  confirm(template: TemplateRef<any>, id: number, index: number) {
    this.idCurrPoll = id;
    this.indexCurrPoll = index;
    this.modalRef = this.modalService.show(template);
  }

  editPoll(id: number, index: number) {
    localStorage.setItem('index', (0).toString());
    this.pollService.currPoll = this.polls[index];
    this.router.navigate(['/constructorPoll']);

  }

  transferToStats(id: number, index: number) {
    localStorage.setItem('index', (2).toString());
    this.pollService.currPoll = this.polls[index];
    this.router.navigate(['/constructorPoll']);
  }

  createNewPoll() {
    this.pollService.currPoll=null;
    //localStorage.setItem('idCurrPoll', null);
    this.modalRef.hide();
    this.router.navigate(['titleNewPoll']);
  }

  goToTemplates() {
    this.modalRef.hide();
    this.router.navigate(['creatingByTemplate']);
  }

  pageChanged(event: PageChangedEvent) {
    this.currentPage = event.page;
    console.log(this.currentPage);
    this.getPolls(this.currentPage);
  }

  getPolls(page: number) {
    this.currentPage = page;
    this.subs[this.subs.length] = this.pollService.getPollsPageByUserId(this.idUser,
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

  newTemplate() {
    this.pollService.currPoll=null;
    this.router.navigate(['titleNewPoll']);
  }


//admin
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
}
