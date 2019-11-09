import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserModel} from "../modules/models/user.model";

@Injectable()
export class UserService{

  constructor(private http: HttpClient){}

  getUserByEmailAndPassword(email:string,password:string): Observable<UserModel> {
    return this.http.get<UserModel>('/api/user/signin?email='+email+'&password='+password);
  }

  getUserById(id:number):Observable<UserModel>{
    return this.http.get<UserModel>('/api/user/id?id='+id);
  }

  saveUser(user:UserModel):Observable<UserModel>{
    return this.http.post<UserModel>('/api/user/registration', user);
  }

  getAllUsers():Observable<UserModel[]>{
    return this.http.get<UserModel[]>('/api/user/all');
  }
}
