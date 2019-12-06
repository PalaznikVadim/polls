import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {BsDropdownModule} from "ngx-bootstrap/dropdown";
import {TooltipModule} from "ngx-bootstrap/tooltip";
import {ModalModule} from "ngx-bootstrap/modal";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


import {AppComponent} from "./app.component";
import {HttpClientModule} from "@angular/common/http";
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {RouterModule, Routes} from "@angular/router";
import {SignInComponent} from './modules/components/sign-in/sign-in.component';
import {RegistrationComponent} from './modules/components/registration/registration.component';
import {HomePageComponent} from './modules/components/home-page/home-page.component';
import {NewPollTitleComponent} from './modules/components/new-poll-title/new-poll-title.component';
import {DesignerComponent} from './modules/components/designer/designer.component';
import {NavComponent} from './modules/components/nav.component/nav.component';
import {GenerateLinkComponent} from './modules/components/generate-link/generate-link.component';
import {PollService} from "./services/poll.service";
import {PollComponent} from './modules/components/poll/poll.component';
import {AccordionModule, ButtonsModule, PaginationModule, TabsModule} from "ngx-bootstrap";
import {FirstPagePollComponent} from './modules/components/first-page-poll/first-page-poll.component';
import {LastPagePollComponent} from './modules/components/last-page-poll/last-page-poll.component';
import {StatsComponent} from './modules/components/stats/stats.component';
import {AdminHomePageComponent} from './modules/components/administrator/admin-home-page/admin-home-page.component';
import {UserListComponent} from './modules/components/administrator/user-list/user-list.component';
import {UserService} from "./services/user.service";
import {ThemeService} from "./services/theme.service";
import {QuestionService} from "./services/question.service";
import {AnswerService} from "./services/answer.service";
import {UserAnswerService} from "./services/user-answer.service";
import {UserHomeComponent} from './modules/components/user-home/user-home.component';
import {ViewUserPollComponent} from './modules/components/administrator/view-user-poll/view-user-poll.component';
import {StatsService} from "./services/stats.service";
import {ProfileComponent} from './modules/components/administrator/profile/profile.component';
import {TemplatesComponent} from './modules/components/administrator/templates/templates.component';
import {ListTemplatesComponent} from './modules/components/list-templates/list-templates.component';
import {ErrorService} from "./services/error.service";
import { ErrorPageComponent } from './modules/components/error-page/error-page.component';
import { PageWithPollsComponent } from './modules/components/page-with-polls/page-with-polls.component';


const appRoutes: Routes = [
  {path: "", component: SignInComponent},
  {path: "registration", component: RegistrationComponent},
  {path: "homePage", component: HomePageComponent},
  {path: "titleNewPoll", component: NewPollTitleComponent},
  {path: 'poll', component: PollComponent},
  {path: 'firstPagePoll/:link', component: FirstPagePollComponent},
  {path: 'lastPagePoll', component: LastPagePollComponent},
  {path: 'viewUserPoll', component: ViewUserPollComponent},
  {path: 'constructorPoll', component: NavComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'templates', component: TemplatesComponent},
  {path: "creatingByTemplate", component: ListTemplatesComponent},
  {path:'myPolls',component: PageWithPollsComponent},
  {path:'myTemplates',component: PageWithPollsComponent},
  {path:'userPolls',component: PageWithPollsComponent},
  {path: "**",component:ErrorPageComponent}


];

@NgModule({
  declarations: [
    AppComponent,
    SignInComponent,
    RegistrationComponent,
    HomePageComponent,
    NewPollTitleComponent,
    DesignerComponent,
    NavComponent,
    GenerateLinkComponent,
    PollComponent,
    FirstPagePollComponent,
    LastPagePollComponent,
    StatsComponent,
    AdminHomePageComponent,
    UserListComponent,
    UserHomeComponent,
    ViewUserPollComponent,
    ProfileComponent,
    TemplatesComponent,
    ListTemplatesComponent,
    ErrorPageComponent,
    PageWithPollsComponent,

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
    ErrorService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
