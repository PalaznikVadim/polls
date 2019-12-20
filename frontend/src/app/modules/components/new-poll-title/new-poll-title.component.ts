import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {PollModel} from "../../models/poll.model";
import {ThemeService} from "../../../services/theme.service";
import {ThemeModel} from "../../models/theme.model";
import {PollService} from "../../../services/poll.service";
import {Router} from "@angular/router";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {ErrorModel} from "../../models/error.model";
import {HttpErrorResponse} from "@angular/common/http";
import {UserService} from "../../../services/user.service";
import {ErrorService} from "../../../services/error.service";

@Component({
  selector: 'app-new-poll-title',
  templateUrl: './new-poll-title.component.html',
  styleUrls: ['./new-poll-title.component.css']
})
export class NewPollTitleComponent implements OnInit, OnDestroy {

  role: string;
  poll: PollModel;
  themes: ThemeModel[];
  subs: any[];
  countSubs = 0;
  newTheme: string;
  errors: ErrorModel[] = [];

  constructor(private modalService: BsModalService, private themeService: ThemeService, private pollService: PollService,
              private userService: UserService, private router: Router, private errorService: ErrorService) {
  }

  modalRef: BsModalRef;
  config = {
    animated: true
  };

  ngOnInit() {
    this.subs = [];
    this.role = this.userService.currUser.role;
    this.subs[this.countSubs++] = this.themeService.getAllTheme().subscribe(value => {
      this.themes = value as ThemeModel[];

      if (this.pollService.currPoll == null) {
        this.poll = new PollModel();
        this.poll.theme = this.themes[0].name;
      } else {
        this.poll = this.pollService.currPoll;
      }
    });
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  addPoll() {
    if (this.poll.id == null) {
      this.poll.idUser = this.userService.currUser.id;
      if (this.userService.currUser.role == 'admin') {
        this.poll.shared = 'Yes';
      }else {
        this.poll.shared = 'No';
      }
      this.poll.status = 'active';
    }
    console.log(this.poll);
    this.subs[this.countSubs++] = this.pollService.savePoll(this.poll).subscribe(value => {
        localStorage.setItem('index', (0).toString());
        this.pollService.currPoll = value;
        if(this.userService.currUser.role=='user') {
          this.router.navigate(['creation_poll']);
        }else {
          this.router.navigate(['templateCreation'])
        }
      }, response => {
        if (this.errors != null)
          this.errors = [];
        let resError;
        for (let i = 0; i < response.error.length; i++) {
          resError = new ErrorModel();
          resError.field = (response as HttpErrorResponse).error[i].field;
          resError.defaultMessage = (response as HttpErrorResponse).error[i].defaultMessage;
          this.errors.push(resError);
        }
      }
    );
  }

  ngOnDestroy(): void {
    if (this.subs != null)
      for (let i = 0; i < this.subs.length; i++)
        this.subs[i].unsubscribe();

  }

  addNewTheme(newTheme: string) {
    this.subs[this.countSubs++] = this.themeService.save(newTheme).subscribe(theme => {
      this.themes.push(theme);
      this.modalRef.hide();
    }, errorResponse => {
      this.errors = [];
      const errors = errorResponse.error;
      for (let key in errors) {
        for (let i = 0; i < errors[key].length; i++) {
          const errorResponse = new ErrorModel();
          errorResponse.field = key;
          errorResponse.defaultMessage = errors[key][i];
          this.errors.push(errorResponse);
        }
      }
    });
  }
}
