import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {PollService} from "../poll.service";
import {Injectable} from "@angular/core";

@Injectable()
export class PollGuardService implements CanActivate {
  constructor(private router: Router, private pollService: PollService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.hasPollId()) {
      localStorage.setItem('err','Without a selected poll, access actions are not available');
      this.router.navigate(['/home']);
      return false;
    }
    return true;
  }

  private hasPollId() {
    if (this.pollService.currPoll) {
      console.log(this.pollService.currPoll);
      return true;
    } else {
      console.log(this.pollService.currPoll);
      return false;
    }
  }
}
