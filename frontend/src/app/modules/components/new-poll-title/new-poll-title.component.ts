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
      console.log(this.userService.currUser);
      console.log(this.themes);
    })
  }

  addPoll() {
    this.themeService.getThemeByName(this.themeStr).subscribe(theme=>{
      this.poll.idUser=this.userService.currUser.id;
      this.poll.idTheme=theme.id;
      this.poll.shared='Yes';
      this.poll.status='active';
      this.pollService.savePoll(this.poll).subscribe(value => {
        this.pollService.currPoll=value as PollModel;
        console.log(this.pollService.currPoll);
        this.router.navigate(['/designer']);
      });
    });
  }
}
