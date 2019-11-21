import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {PollModel} from "../../models/poll.model";
import {BsModalRef, BsModalService, PageChangedEvent} from "ngx-bootstrap";
import {PollService} from "../../../services/poll.service";
import {Router} from "@angular/router";
import {RestPageModel} from "../../models/rest-page.model";
import {UserModel} from "../../models/user.model";

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit,OnDestroy {

  private sub:any;
  indexCurrPoll:number;
  idCurrPoll:number;
  polls:PollModel[]=[];
  page:RestPageModel;

  modalRef: BsModalRef;
  config = {
    animated: true
  };
  private currentPage: number=1;

  constructor(private modalService: BsModalService,private  pollService:PollService,private router: Router) {}

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  ngOnInit() {
    this.getPolls();
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  deletePoll(id: number,i:number) {
    this.pollService.deletePoll(id.toString()).subscribe(value=>{
      this.polls.splice(i,1);
      this.modalRef.hide();
      console.log("Poll with id="+id+"deleted");
    });
  }

  confirm(template: TemplateRef<any>,id:number,index:number){
    this.idCurrPoll=id;
    this.indexCurrPoll=index;
    this.modalRef = this.modalService.show(template);
  }

  editPoll(id:number,index:number){
    localStorage.setItem('index',(0).toString());
    this.pollService.currPoll=this.polls[index];
    this.router.navigate(['/constructorPoll']);

  }

  transferToStats(id:number,index:number){
    localStorage.setItem('index',(2).toString());
    //localStorage.setItem("idCurrPoll",id.toString());
    this.pollService.currPoll=this.polls[index];
    this.router.navigate(['/constructorPoll']);
  }

  createNewPoll() {
    localStorage.setItem('idCurrPoll',null);
    this.modalRef.hide();
    this.router.navigate(['titleNewPoll']);
  }

  goToTemplates() {
    this.modalRef.hide();
    this.router.navigate(['creatingByTemplate']);
  }

  pageChanged(event: PageChangedEvent) {
    this.currentPage=event.page;
    console.log(this.currentPage);
    this.getPolls();
  }

  private getPolls() {
    this.sub = this.pollService.getPollsByUserId(Number(localStorage.getItem("idCurrUser")),this.currentPage-1,'name').subscribe(page => {
      this.page = page as RestPageModel;
      console.log(this.page);
      this.polls = this.page.content as PollModel[];
      console.log(this.polls)
    });
  }
}
