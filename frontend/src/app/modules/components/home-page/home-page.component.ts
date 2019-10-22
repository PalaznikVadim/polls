import {Component, OnInit, TemplateRef} from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import {PollModel} from "../../models/poll.model";


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
poll=new PollModel();

  polls:PollModel[]=[this.poll,this.poll];

  modalRef: BsModalRef;
  config = {
    animated: true
  };

  constructor(private modalService: BsModalService) {}

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  ngOnInit() {
    this.poll.name="poll1";
    this.poll.date=(new Date);
    this.poll.description="fsdsfddsdfsfdfsfdfdd"
  }

}
