import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { TooltipModule } from "ngx-bootstrap/tooltip";
import { ModalModule } from "ngx-bootstrap/modal";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


import { AppComponent } from "./app.component";
import {HttpClientModule} from "@angular/common/http";
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {RouterModule, Routes} from "@angular/router";
import { SignInComponent } from './modules/components/sign-in/sign-in.component';
import { RegistrationComponent } from './modules/components/registration/registration.component';
import { HomePageComponent } from './modules/components/home-page/home-page.component';
import { NewPollTitleComponent } from './modules/components/new-poll-title/new-poll-title.component';
import { DesignerComponent } from './modules/components/designer/designer.component';
import { NavComponent } from './modules/components/nav.component/nav.component';
import { GenerateLinkComponent } from './modules/components/generate-link/generate-link.component';
import {PollService} from "./services/poll.service";
import { PollComponent } from './modules/components/poll/poll.component';
import {ButtonsModule} from "ngx-bootstrap";
import { FirstPagePollComponent } from './modules/components/first-page-poll/first-page-poll.component';
import { LastPagePollComponent } from './modules/components/last-page-poll/last-page-poll.component';
import { StatsComponent } from './modules/components/stats/stats.component';
import { AdminHomePageComponent } from './modules/components/administrator/admin-home-page/admin-home-page.component';
import { UserListComponent } from './modules/components/administrator/user-list/user-list.component';
import {UserService} from "./services/user.service";
import {ThemeService} from "./services/theme.service";




const appRoutes: Routes = [
  {path: "",component:SignInComponent},
  {path:"registration",component:RegistrationComponent},
  {path:"homePage",component:HomePageComponent},
  {path:"titleNewPoll",component:NewPollTitleComponent},
  {path:"designer",component:DesignerComponent},
  {path:"link",component:GenerateLinkComponent},
  {path:'poll',component:PollComponent},
  {path:'firstPagePoll',component:FirstPagePollComponent},
  {path:'lastPagePoll',component:LastPagePollComponent},
  {path:'stats',component:StatsComponent}





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
    ButtonsModule.forRoot()
  ],
  providers: [
    PollService,
    UserService,
    ThemeService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
