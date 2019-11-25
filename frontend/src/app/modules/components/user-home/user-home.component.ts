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

  subs:any;
  countSubs:number=0;
  indexCurrPoll:number;
  idCurrPoll:number;
  polls:PollModel[]=[];
  page:RestPageModel;
  currentPage: number=1;
  prevSort:string='name';
  sort: string='name';
  prevSize:number=6;
  size: number=6;
  searchResult:string;

  modalRef: BsModalRef;
  config = {
    animated: true
  };
  search: string='';



  constructor(private modalService: BsModalService,private  pollService:PollService,private router: Router) {}

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  ngOnInit() {
    this.subs=[];
    this.getPolls();
  }

  ngOnDestroy(): void {
      for(let i=0;i<this.subs.length;i++)
        this.subs[i].unsubscribe();
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
    this.subs[this.countSubs++] = this.pollService.getPollsByUserId(Number(localStorage.getItem("idCurrUser")),this.currentPage-1,this.size,this.sort).subscribe(page => {
      this.page = page as RestPageModel;
      console.log(this.page);
      this.polls = this.page.content as PollModel[];
      console.log(this.polls)
    });
  }


  selectSort() {
    if(this.prevSort!=this.sort) {
      this.prevSort=this.sort;
      this.getPolls();
    }
  }

  searchPoll() {
    if(this.search!=''){
      this.subs[this.countSubs++]=this.pollService.searchPollsBySubstr(this.search,Number(localStorage.getItem('idCurrUser')),this.currentPage-1,this.size,this.sort).subscribe(page=>{
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
    console.log(this.size);
    if(this.prevSize!=this.size){
      this.currentPage=1;
      this.getPolls();
      this.prevSize=this.size;
    }
  }
}
