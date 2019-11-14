import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {PollModel} from "../../models/poll.model";
import {ThemeService} from "../../../services/theme.service";
import {ThemeModel} from "../../models/theme.model";
import {PollService} from "../../../services/poll.service";
import {UserService} from "../../../services/user.service";
import {Router} from "@angular/router";
import {BsModalRef, BsModalService} from "ngx-bootstrap";

@Component({
  selector: 'app-new-poll-title',
  templateUrl: './new-poll-title.component.html',
  styleUrls: ['./new-poll-title.component.css']
})
export class NewPollTitleComponent implements OnInit,OnDestroy {

  role:string;
  poll:PollModel;
  themes:ThemeModel[];
  subs:any[];
  countSubs=0;
  newTheme:string;

  constructor(private modalService: BsModalService, private themeService:ThemeService,private pollService:PollService,private router: Router) {
  }

  modalRef: BsModalRef;
  config = {
    animated: true
  };

  ngOnInit() {
    this.subs=[];
    this.role=localStorage.getItem('currRole');
    this.subs[this.countSubs++]=this.themeService.getAllTheme().subscribe(value=> {
      this.themes = value as ThemeModel[];
      if (localStorage.getItem('idCurrPoll') == null) {
        this.poll = new PollModel();
        this.poll.theme = this.themes[0].name;
      } else {
        this.pollService.getPollById(Number(localStorage.getItem('idCurrPoll'))).subscribe(poll => {
          this.poll = poll;
          console.log(this.poll);
        })
      }
    });
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  addPoll() {
    if(this.poll.id==null) {
      this.poll.idUser = Number(localStorage.getItem("idCurrUser"));
      if (localStorage.getItem('currRole') == 'admin')
        this.poll.shared = 'Yes';
      else
        this.poll.shared = 'No';
      this.poll.status = 'active';
    }
    console.log(this.poll);
    this.subs[this.countSubs++]=this.pollService.savePoll(this.poll).subscribe(value => {
      localStorage.setItem('idCurrPoll',value.id.toString());
      localStorage.setItem('index',(0).toString());
      this.router.navigate(['/constructorPoll']);
    });
  }

  ngOnDestroy(): void {
    if(this.subs!=null)
      for (let i=0;i<this.subs.length;i++)
        this.subs[i].unsubscribe();

  }

  addNewTheme(newTheme: string) {
    this.subs[this.countSubs++]=this.themeService.save(newTheme).subscribe(theme=>{
      this.themes.push(theme);
      this.modalRef.hide();
    });
  }
}
