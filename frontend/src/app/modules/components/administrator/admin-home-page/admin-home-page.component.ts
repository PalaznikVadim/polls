import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-home-page',
  templateUrl: './admin-home-page.component.html',
  styleUrls: ['./admin-home-page.component.css']
})
export class AdminHomePageComponent implements OnInit {

  tabs:boolean[];


  constructor() { }

  ngOnInit() {
    this.tabs=[true,false,false];
  }

  changeContent(index:number):void{
    for(let i=0;i<this.tabs.length;i++)
      this.tabs[i]=false;
    this.tabs[index]=true;
  }
}
