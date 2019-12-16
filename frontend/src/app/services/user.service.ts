import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserModel} from "../modules/models/user.model";
import {RestPageModel} from "../modules/models/rest-page.model";


@Injectable()
export class UserService {
  private _currUser: UserModel;
  private _idSelectedUser: number;

  constructor(private http: HttpClient) {
  }

  getUserByEmailAndPassword(email: string, password: string): Observable<any> {
    return this.http.get<any>('/api/user/signin?email=' + email + '&password=' + password);
  }

  saveUser(user: UserModel): Observable<any> {
    return this.http.post<any>('/api/user/registration', user);
  }

  getAllUsers(search: string, page: number, size: number, sort: string, order: string): Observable<RestPageModel> {
    return this.http.get<RestPageModel>('/api/user?search=' + search + '&page=' + page + '&size=' + size +
      '&sort=' + sort + '&order=' + order);
  }

  get currUser(): UserModel {
    return this._currUser;
  }

  set currUser(value: UserModel) {
    this._currUser = value;
  }

  get idSelectedUser(): number {
    return this._idSelectedUser;
  }

  set idSelectedUser(value: number) {
    this._idSelectedUser = value;
  }
}
