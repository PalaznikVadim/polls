import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs";
import {UserService} from "../user.service";

@Injectable()
export class SelectedUserGuard implements CanActivate {
  constructor(private router: Router, private userService: UserService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.hasSelectedUser()) {
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
