import { Component, OnInit } from '@angular/core';
import {PollService} from "../../../services/poll.service";

@Component({
  selector: 'app-generate-link',
  templateUrl: './generate-link.component.html',
  styleUrls: ['./generate-link.component.css']
})
export class GenerateLinkComponent implements OnInit {

  constructor(private pollService:PollService) { }

  ngOnInit() {

  }

}
