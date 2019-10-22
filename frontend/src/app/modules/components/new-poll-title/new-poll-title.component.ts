import { Component, OnInit } from '@angular/core';
import {PollModel} from "../../models/poll.model";

@Component({
  selector: 'app-new-poll-title',
  templateUrl: './new-poll-title.component.html',
  styleUrls: ['./new-poll-title.component.css']
})
export class NewPollTitleComponent implements OnInit {

  poll = new PollModel();

  constructor() {
  }

  ngOnInit() {
  }

  addPoll() {
    console.log(this.poll);
  }
}
