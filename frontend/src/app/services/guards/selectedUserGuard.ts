import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {UserService} from "../user.service";

@Injectable()
export class SelectedUserGuard implements CanActivate {
  constructor(private router: Router, private userService: UserService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    console.log('selected', localStorage.getItem('token'));
    if (!this.hasSelectedUser()) {
      localStorage.setItem('err', 'To view the profile you must select a user');
      this.router.navigate(['/userTable']);
      return false;
    }
    return true;
  }

  private hasSelectedUser() {
    if (this.userService.idSelectedUser) {
      return true;
    } else {
      return false;
    }
  }
}
