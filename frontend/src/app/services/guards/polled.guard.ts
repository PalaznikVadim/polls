import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {PollService} from "../poll.service";
import {Observable} from "rxjs";

@Injectable()
export class PolledGuardService implements CanActivate {
  constructor(private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.hasPollId()) {
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }

  private hasPollId() {
    if (localStorage.getItem('idCurrPoll')) {
      return true;
    } else {
      return false;
    }
  }
}

