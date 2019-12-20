import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-mess-component',
  templateUrl: './mess-component.component.html',
  styleUrls: ['./mess-component.component.css']
})
export class MessComponentComponent implements OnInit {

  errorMessage:string;
  dismissible = true;

  constructor() { }

  ngOnInit() {
    if(localStorage.getItem('err')){
      this.errorMessage=localStorage.getItem('err');
      localStorage.removeItem('err');
    }
  }
}
