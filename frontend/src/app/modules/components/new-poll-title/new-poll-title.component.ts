import {Component, OnDestroy, OnInit} from '@angular/core';
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
export class NewPollTitleComponent implements OnInit,OnDestroy {

  themeStr:string;
  poll = new PollModel();
  themes:ThemeModel[];
  subs:any[];
  countSubs=0;

  constructor(private themeService:ThemeService,private pollService:PollService,private userService:UserService,private router: Router) {
  }

  ngOnInit() {
    this.subs=[];
    this.subs[this.countSubs++]=this.themeService.getAllTheme().subscribe(value=>{
      this.themes=value as ThemeModel[];
      this.themeStr=this.themes[0].name;
    });

  }

  addPoll() {
console.log(this.themeStr);

      this.poll.idUser=Number(localStorage.getItem("idCurrUser"));
      this.poll.idTheme=this.getIdThemeByName(this.themeStr,this.themes);
      this.poll.shared='Yes';
      this.poll.status='active';
      this.subs[this.countSubs++]=this.pollService.savePoll(this.poll).subscribe(value => {
        localStorage.setItem('idCurrPoll',value.id.toString());
        localStorage.setItem('index',null);
        this.router.navigate(['/constructorPoll']);
      });
  }

  getIdThemeByName(name:string,list:ThemeModel[]):number{
    for(let i=0;i<list.length+1;i++){
      if(list[i].name==name) {
        return list[i].id;
      }
    }
    return null;
  }

  ngOnDestroy(): void {
    if(this.subs!=null)
      for (let i=0;i<this.subs.length;i++)
        this.subs[i].unsubscribe();

  }
}
