import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserModel} from "../modules/models/user.model";
import {RestPageModel} from "../modules/models/rest-page.model";

@Injectable()
export class UserService{
  private _currUser:UserModel;
  private _reqHeader:HttpHeaders;

  constructor(private http: HttpClient){
    this._reqHeader=new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer_' + localStorage.getItem('token')
    });
  }

  getUserByEmailAndPassword(email:string,password:string): Observable<any> {
    return this.http.get<any>('/api/user/signin?email='+email+'&password='+password);
  }

  saveUser(user:UserModel):Observable<any>{
    return this.http.post<any>('/api/user/registration', user);
  }

  getAllUsers(page:number,size:number,sort:string,order:string):Observable<RestPageModel>{
    return this.http.get<RestPageModel>('/api/user/?page='+page+'&size='+size+'&sort='+sort+'&order='+order,{headers:this._reqHeader});
  }

  get currUser(): UserModel {
    return this._currUser;
  }

  set currUser(value: UserModel) {
    this._currUser = value;
  }

  get reqHeader(): HttpHeaders {
    return this._reqHeader;
  }

  set reqHeader(value: HttpHeaders) {
    this._reqHeader = value;
  }
}
