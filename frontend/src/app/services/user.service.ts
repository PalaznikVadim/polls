import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserModel} from "../modules/models/user.model";
import {RestPageModel} from "../modules/models/rest-page.model";

@Injectable()
export class UserService{

  constructor(private http: HttpClient){}

  getUserByEmailAndPassword(email:string,password:string): Observable<UserModel> {
    return this.http.get<UserModel>('/api/user/signin?email='+email+'&password='+password);
  }

  getUserById(id:number):Observable<UserModel>{
    return this.http.get<UserModel>('/api/user/id?id='+id);
  }

  saveUser(user:UserModel):Observable<any>{
    return this.http.post<any>('/api/user/registration', user);
  }

  getAllUsers(page:number,size:number,sort:string,order:string):Observable<RestPageModel>{
    return this.http.get<RestPageModel>('/api/user/?page='+page+'&size='+size+'&sort='+sort+'&order='+order);
  }
}
