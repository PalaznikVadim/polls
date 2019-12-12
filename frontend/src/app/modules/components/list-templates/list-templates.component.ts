import { Component, OnInit } from '@angular/core';
import {ThemeService} from "../../../services/theme.service";
import {PollService} from "../../../services/poll.service";
import {Router} from "@angular/router";
import {ThemeModel} from "../../models/theme.model";
import {PollModel} from "../../models/poll.model";
import {ClonePollModel} from "../../models/clonePoll.model";
import {UserService} from "../../../services/user.service";

@Component({
  selector: 'app-list-templates',
  templateUrl: './list-templates.component.html',
  styleUrls: ['./list-templates.component.css']
})
export class ListTemplatesComponent implements OnInit {

  isFirstOpen: boolean = true;
  themes:ThemeModel[];
  subs:any[];
  countSubs=0;
  clonePoll:ClonePollModel;


  constructor(private themeService:ThemeService,private userService:UserService,private pollService:PollService,private router:Router){ }

  ngOnInit() {
    this.clonePoll=new ClonePollModel();
    this.subs=[];
    this.themes=[];
    this.subs[this.countSubs++]=this.themeService.getAllTemplateThemes().subscribe(themes=>{
      for(let i=0;i<themes.length;i++) {
        this.themes.push(new ThemeModel());
        this.themes[i].name = themes[i];
      }
      this.subs[this.countSubs++]=this.pollService.getAllTemplatesByTheme(this.themes[0].name).subscribe(polls=>{
        this.themes[0].polls=polls;
      });
    });


  }

  loadPolls(i:number) {
    if(this.themes[i].polls==null){
      this.subs[this.countSubs++]=this.pollService.getAllTemplatesByTheme(this.themes[i].name).subscribe(polls=>{
        this.themes[i].polls=polls;
      });
    }

  }

  goToDesigner(id: number) {
    this.clonePoll.id=id;
    this.clonePoll.idUser=this.userService.currUser.id;
    this.subs[this.countSubs++]=this.pollService.clonePoll(this.clonePoll).subscribe(poll=>{
      this.pollService.currPoll=poll;
      this.router.navigate(['newPoll']);
    });
  }
}
