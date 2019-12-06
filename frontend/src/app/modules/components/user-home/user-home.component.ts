import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {PollModel} from "../../models/poll.model";
import {BsModalRef, BsModalService, PageChangedEvent} from "ngx-bootstrap";
import {PollService} from "../../../services/poll.service";
import {Router} from "@angular/router";
import {RestPageModel} from "../../models/rest-page.model";
import {ThemeService} from "../../../services/theme.service";
import {map, tap} from "rxjs/operators";
import {UserService} from "../../../services/user.service";


@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit, OnDestroy {

  subs: any;
  countSubs: number = 0;
  indexCurrPoll: number;
  idCurrPoll: number;
  polls: PollModel[] = [];
  page: RestPageModel;
  currentPage: number = 1;
  sort: string = 'name';
  size: number = 6;
  order: string = 'ASC';
  searchResult: string;
  search: string = '';
  themes: string[];


  modalRef: BsModalRef;
  config = {
    animated: true
  };
  select: string;


  constructor(private modalService: BsModalService, private  pollService: PollService, private router: Router, private themeService: ThemeService,private userService:UserService) {
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  ngOnInit() {
    this.select = 'all';
    this.subs = [];
    this.getPolls(this.currentPage);
    this.subs[this.countSubs++] = this.themeService.getUserPollThemes(this.userService.currUser.id).subscribe(themes => {
      this.themes = themes;
    });
  }

  ngOnDestroy(): void {
    for (let i = 0; i < this.subs.length; i++)
      this.subs[i].unsubscribe();
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
