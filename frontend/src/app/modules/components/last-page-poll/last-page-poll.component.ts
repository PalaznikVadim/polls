import {Component, OnInit} from '@angular/core';
import {PollService} from "../../../services/poll.service";
import {NavigationStart, Router, RoutesRecognized} from "@angular/router";
import {filter, pairwise} from "rxjs/operators";

@Component({
  selector: 'app-last-page-poll',
  templateUrl: './last-page-poll.component.html',
  styleUrls: ['./last-page-poll.component.css']
})
export class LastPagePollComponent implements OnInit {

  constructor(private router: Router) {

  }

  ngOnInit() {
    localStorage.removeItem('idCurrPoll');
    this.router.events
      .pipe(filter((e: any) => e instanceof RoutesRecognized),
        pairwise()
      ).subscribe((e: any) => {
      console.log(e[0].urlAfterRedirects); // previous url
    });
  }
}
