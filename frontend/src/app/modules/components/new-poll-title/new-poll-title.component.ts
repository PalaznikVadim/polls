import { Component, OnInit } from '@angular/core';
import {PollModel} from "../../models/poll.model";
import {ThemeService} from "../../../services/theme.service";
import {ThemeModel} from "../../models/theme.model";
import {PollService} from "../../../services/poll.service";
import {UserService} from "../../../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-poll-title',
  templateUrl: './new-poll-title.component.html',
  styleUrls: ['./new-poll-title.component.css']
})
export class NewPollTitleComponent implements OnInit {

  themeStr:string;
  poll = new PollModel();
  themes:ThemeModel[];

  constructor(private themeService:ThemeService,private pollService:PollService,private userService:UserService,private router: Router) {
  }

  ngOnInit() {
    this.themeService.getAllTheme().subscribe(value=>{
      this.themes=value as ThemeModel[];
    })
  }

  addPoll() {

      this.poll.idUser=Number(localStorage.getItem("idCurrUser"));
      this.poll.idTheme=this.getIdThemeByName(this.themeStr,this.themes);
      this.poll.shared='Yes';
      this.poll.status='active';
      this.pollService.savePoll(this.poll).subscribe(value => {
        localStorage.setItem('idCurrPoll',value.id.toString());
        //this.pollService.currPoll=value as PollModel;
        //console.log(this.pollService.currPoll);
        this.router.navigate(['/designer']);
      });
  }

  getIdThemeByName(name:string,list:ThemeModel[]):number{
    for(let i=0;i<list.length;i++){
      if(list[i].name===name)
        return list[i].id;
      return null;
    }
  }
}
