import { Component, OnInit } from '@angular/core';
import {PollModel} from "../../models/poll.model";
import {ThemeService} from "../../../services/theme.service";
import {ThemeModel} from "../../models/theme.model";
import {PollService} from "../../../services/poll.service";

@Component({
  selector: 'app-new-poll-title',
  templateUrl: './new-poll-title.component.html',
  styleUrls: ['./new-poll-title.component.css']
})
export class NewPollTitleComponent implements OnInit {

  themeStr:string;
  poll = new PollModel();
  themes:ThemeModel[];

  constructor(private themeService:ThemeService,private pollService:PollService) {
  }

  ngOnInit() {
    this.themeService.getAllTheme().subscribe(value=>{
      this.themes=value as ThemeModel[];
      console.log(this.themes);
    })
  }

  addPoll() {
    this.themeService.getThemeByName(this.themeStr).subscribe(theme=>{
      this.poll.theme=theme as ThemeModel;
      this.poll.date=new Date();
      this.pollService.savePoll(this.poll).subscribe(value => {
        this.poll=value;
        console.log(this.poll);
      });
    });

    console.log(this.poll);
  }
}
