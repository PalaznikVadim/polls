import {Component, OnInit} from '@angular/core';
import {PollService} from "../../../services/poll.service";

@Component({
  selector: 'app-generate-link',
  templateUrl: './generate-link.component.html',
  styleUrls: ['./generate-link.component.css']
})
export class GenerateLinkComponent implements OnInit {

  link: string;

  constructor(private pollService: PollService) {
  }

  ngOnInit() {
    this.link = 'localhost:4301/poll/' + this.pollService.currPoll.link;
  }

  // public copyToClipboard(): void {
  //   const hElement = document.getElementById('link');
  //   console.log(hElement.innerText.);
  //
  // }

}
