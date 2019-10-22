import {Component, OnInit, TemplateRef} from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import {PollModel} from "../../models/poll.model";
import {BillingAccountService} from "../../../services/billing-account.service";


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
poll=new PollModel();

  date: any;

  polls:PollModel[]=[this.poll,this.poll];

  modalRef: BsModalRef;
  config = {
    animated: true
  };

  constructor(private modalService: BsModalService,private billingService:BillingAccountService) {}

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  ngOnInit() {
    this.date = this.billingService.getBillingAccounts();

    // this.billingService.getBillingAccounts().subscribe(value => {
    //   this.date = value;
    // });
    this.poll.name="poll1";
    this.poll.date=(new Date);
    this.poll.description="fsdsfddsdfsfdfsfdfdd"
  }

}
