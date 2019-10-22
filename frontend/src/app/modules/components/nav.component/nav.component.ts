import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  activePoll:boolean;
  activeLink:boolean;
  activeStats:boolean;

  constructor(private router: Router) { }

  ngOnInit() {
    this.activePoll=(this.router.url=="/designer");
    this.activeLink=(this.router.url=="/link");

  }

}
