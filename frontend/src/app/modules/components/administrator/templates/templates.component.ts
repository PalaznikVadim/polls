import {Component, OnInit, TemplateRef} from '@angular/core';
import {PollService} from "../../../../services/poll.service";
import {PollModel} from "../../../models/poll.model";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Router} from "@angular/router";

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



  constructor(private pollService:PollService,private modalService: BsModalService,private router:Router) { }

  ngOnInit() {
    this.subs=[];
    this.polls=[];
    this.subs[this.countSubs++]=this.pollService.getPollsByUserId(Number(localStorage.getItem('idCurrUser'))).subscribe(polls=>{
      this.polls=polls;
    });
  }

  modalRef: BsModalRef;
  config = {
    animated: true
  };

  newTemplate() {
    localStorage.setItem('idCurrPoll',null);
    this.router.navigate(['titleNewPoll']);
  }

  editClick(id: number) {
    localStorage.setItem('index',(0).toString());
    localStorage.setItem("idCurrPoll",id.toString());
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
}
