import {Component, OnInit, TemplateRef} from '@angular/core';
import {PollService} from "../../../../services/poll.service";
import {PollModel} from "../../../models/poll.model";
import {BsModalRef, BsModalService, PageChangedEvent} from "ngx-bootstrap";
import {Router} from "@angular/router";
import {RestPageModel} from "../../../models/rest-page.model";

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
  size:number=6;
  prevSize:number=6;
  currentPage: number=1;
  prevSort:string='name';
  sort:string='name';
  search: string;
  private searchResult: string;


  constructor(private pollService:PollService,private modalService: BsModalService,private router:Router) { }

  ngOnInit() {
    this.subs=[];
    this.polls=[];
    this.getPolls();
  }

  modalRef: BsModalRef;
  config = {
    animated: true
  };


  newTemplate() {
    localStorage.setItem('idCurrPoll',null);
    this.router.navigate(['titleNewPoll']);
  }

  editClick(poll:PollModel) {
    localStorage.setItem('index',(0).toString());
    localStorage.setItem("idCurrPoll",poll.id.toString());
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
    this.getPolls();
  }

  private getPolls() {
    this.subs[this.countSubs++]=this.pollService.getPollsByUserId(Number(localStorage.getItem('idCurrUser')),this.currentPage-1,this.size,this.sort).subscribe(page=>{
      this.polls=page.content as PollModel[];
      this.page=page;
    });
  }

  selectSize() {
    if(this.prevSize!=this.size){
      this.prevSize=this.size;
      this.getPolls();
    }
  }

  selectSort() {
    if(this.sort!=this.prevSort){
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
}
