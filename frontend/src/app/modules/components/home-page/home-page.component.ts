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
  poll=new PollModel();

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
    // this.userService.getUserById(6).subscribe(user=> {
    //   this.sub = this.pollService.getPollsByUser(user).subscribe(value => {
    //     this.polls = value;
    //     console.log(this.polls)
    //   });
    // });

    this.pollService.getPollById(1).subscribe(poll=>{
      console.log(poll)
    });


  }

  ngOnDestroy(): void {
    //this.sub.unsubscribe();
  }
}
