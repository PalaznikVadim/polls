import {Component, OnInit, OnDestroy, TemplateRef} from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import {PollModel} from "../../models/poll.model";
import {PollService} from "../../../services/poll.service";
import {UserService} from "../../../services/user.service";


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit,OnDestroy {

  private sub:any;

  polls:PollModel[];

  modalRef: BsModalRef;
  config = {
    animated: true
  };

  constructor(private modalService: BsModalService,private  pollService:PollService,private userService:UserService) {}

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
      console.log("Poll with id="+id+"deleted");
    });

  }
}
