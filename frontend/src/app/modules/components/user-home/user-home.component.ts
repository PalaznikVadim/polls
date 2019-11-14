import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {PollModel} from "../../models/poll.model";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {PollService} from "../../../services/poll.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent implements OnInit,OnDestroy {

  private sub:any;
  indexCurrPoll:number;
  idCurrPoll:number;
  polls:PollModel[];

  modalRef: BsModalRef;
  config = {
    animated: true
  };

  constructor(private modalService: BsModalService,private  pollService:PollService,private router: Router) {}

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  ngOnInit() {
    this.sub = this.pollService.getPollsByUserId(Number(localStorage.getItem("idCurrUser"))).subscribe(value => {
      this.polls = value as PollModel[];
      console.log(this.polls)
    });
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

  editPoll(id:number){
    localStorage.setItem('index',(0).toString());
    localStorage.setItem("idCurrPoll",id.toString());
    this.router.navigate(['/constructorPoll']);
  }

  transferToStats(id:number){
    localStorage.setItem('index',(2).toString());
    localStorage.setItem("idCurrPoll",id.toString());
    this.router.navigate(['/constructorPoll']);
  }

  createNewPoll() {
    localStorage.setItem('idCurrPoll',null);
    this.modalRef.hide();
    this.router.navigate(['titleNewPoll']);
  }
}
