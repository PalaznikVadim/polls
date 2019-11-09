import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  tabs:boolean[];

  constructor(private router: Router) { }

  ngOnInit() {
    if(localStorage.getItem('index')!=null){
      this.tabs=[false,false,false];
      this.tabs[Number(localStorage.getItem('index'))]=true;
    }else{
      this.tabs=[true,false,false];
    }
    console.log(this.tabs);
  }

  changeTab(index:number):void{
    for(let i=0;i<this.tabs.length;i++)
      this.tabs[i]=false;
    this.tabs[index]=true;
  }

}
