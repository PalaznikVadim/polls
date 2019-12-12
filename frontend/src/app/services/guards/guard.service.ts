import {UserService} from "../user.service";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";

@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(private userService: UserService, public router: Router) {
  }

  public isAuthenticated(): boolean {
    if (this.userService.currUser){
      console.log(this.userService.currUser);
      return true;
    }
    else {
      console.log(this.userService.currUser);
      return false;
    }
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.isAuthenticated()) {
     localStorage.setItem('err','Not authorized! Log in with your account');
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
}
