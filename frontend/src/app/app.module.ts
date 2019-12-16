import {BrowserModule} from "@angular/platform-browser";
import {APP_INITIALIZER, NgModule} from "@angular/core";
import {BsDropdownModule} from "ngx-bootstrap/dropdown";
import {TooltipModule} from "ngx-bootstrap/tooltip";
import {ModalModule} from "ngx-bootstrap/modal";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


import {AppComponent} from "./app.component";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule, HttpResponse} from "@angular/common/http";
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {Router, RouterModule, Routes} from "@angular/router";
import {SignInComponent} from './modules/components/sign-in/sign-in.component';
import {RegistrationComponent} from './modules/components/registration/registration.component';
import {NewPollTitleComponent} from './modules/components/new-poll-title/new-poll-title.component';
import {GenerateLinkComponent} from './modules/components/generate-link/generate-link.component';
import {PollService} from "./services/poll.service";
import {PollComponent} from './modules/components/poll/poll.component';
import {AccordionModule, ButtonsModule, PaginationModule, TabsModule} from "ngx-bootstrap";
import {FirstPagePollComponent} from './modules/components/first-page-poll/first-page-poll.component';
import {LastPagePollComponent} from './modules/components/last-page-poll/last-page-poll.component';
import {StatsComponent} from './modules/components/stats/stats.component';
import {UserService} from "./services/user.service";
import {ThemeService} from "./services/theme.service";
import {QuestionService} from "./services/question.service";
import {AnswerService} from "./services/answer.service";
import {UserAnswerService} from "./services/user-answer.service";
import {StatsService} from "./services/stats.service";
import {ListTemplatesComponent} from './modules/components/list-templates/list-templates.component';
import {ErrorService} from "./services/error.service";
import {ErrorPageComponent} from './modules/components/error-page/error-page.component';
import {PageWithPollsComponent} from './modules/components/page-with-polls/page-with-polls.component';
import {Interceptor} from "./services/interceptor";
import {PollWithQuestionsComponent} from './modules/components/poll-with-questions/poll-with-questions.component';
import {TopNavComponent} from './modules/components/top-nav/top-nav.component';
import {UserListComponent} from "./modules/components/user-list/user-list.component";
import {AuthGuardService} from "./services/guards/guard.service";
import {PollGuardService} from "./services/guards/poll.guard.service";
import {AdminGuardService} from "./services/guards/admin.guard.service";
import {UserGuardService} from "./services/guards/user.guard.service";
import {SelectedUserGuard} from "./services/guards/selectedUserGuard";


const appRoutes: Routes = [
  {path: "", component: SignInComponent},
  {path: "registration", component: RegistrationComponent},
  {path: "newPoll", component: NewPollTitleComponent,canActivate:[AuthGuardService]},
  {path: 'poll', component: PollComponent},
  {path: 'firstPagePoll/:link', component: FirstPagePollComponent},
  {path: 'lastPagePoll', component: LastPagePollComponent},
  {path: 'stats', component: StatsComponent, canActivate: [PollGuardService]},
  {path: 'pollLink', component: GenerateLinkComponent, canActivate: [PollGuardService, UserGuardService]},
  {path: 'userTable', component: UserListComponent, canActivate: [AuthGuardService, AdminGuardService]},
  {path: 'creatingByTemplate', component: ListTemplatesComponent, canActivate: [AuthGuardService, UserGuardService]},
  {path: 'home', component: PageWithPollsComponent, canActivate: [AuthGuardService]},
  {
    path: 'userPolls',
    component: PageWithPollsComponent,
    canActivate: [AuthGuardService, AdminGuardService, SelectedUserGuard]
  },
  {path: 'creation_poll', component: PollWithQuestionsComponent, canActivate: [PollGuardService, UserGuardService]},
  {path: 'templateCreation', component: PollWithQuestionsComponent, canActivate: [PollGuardService, AdminGuardService]},
  {
    path: 'viewUserPoll',
    component: PollWithQuestionsComponent,
    canActivate: [SelectedUserGuard, PollGuardService, AdminGuardService]
  },
  {path: "**", component: ErrorPageComponent}


];

export function loadUser(http: HttpClient, userService: UserService) {
  return () => {
    console.log('init');
    if (localStorage.getItem('token') != null) {
      return http.get<any>('/api/user/' + localStorage.getItem('token'))
        .toPromise()
        .then((resp) => {
          userService.currUser = resp;
          console.log(resp);
        }, errorResponse => {
          if (errorResponse.status == '403') {
            console.log('403', errorResponse);
            console.log('load');
            //localStorage.removeItem('token');
          } else {
            console.log(errorResponse);
          }
        });
    }
  }
}

@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    RegistrationComponent,
    NewPollTitleComponent,
    GenerateLinkComponent,
    PollComponent,
    FirstPagePollComponent,
    LastPagePollComponent,
    StatsComponent,
    UserListComponent,
    ListTemplatesComponent,
    ErrorPageComponent,
    PageWithPollsComponent,
    PollWithQuestionsComponent,
    TopNavComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    Ng4LoadingSpinnerModule.forRoot(),
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    RouterModule.forRoot(appRoutes),
    ReactiveFormsModule,
    ButtonsModule.forRoot(),
    AccordionModule.forRoot(),
    TabsModule.forRoot(),
    PaginationModule.forRoot()
  ],
  providers: [
    PollService,
    UserService,
    ThemeService,
    QuestionService,
    AnswerService,
    UserAnswerService,
    StatsService,
    ErrorService,
    AuthGuardService,
    PollGuardService,
    AdminGuardService,
    UserGuardService,
    SelectedUserGuard,
    {
      provide: APP_INITIALIZER,
      useFactory: loadUser,
      multi: true,
      deps: [HttpClient, UserService]
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
