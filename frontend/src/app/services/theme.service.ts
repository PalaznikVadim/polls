import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {ThemeModel} from "../modules/models/theme.model";
import {observableToBeFn} from "rxjs/internal/testing/TestScheduler";


@Injectable()
export class ThemeService{

  reqHeader:HttpHeaders;

  constructor(private http: HttpClient) {
    this.reqHeader = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer_' + localStorage.getItem('token')
    });
  }

  getAllTheme():Observable<ThemeModel[]>{
    return this.http.get<ThemeModel[]>('/api/theme',{headers:this.reqHeader});
  }

  save(titleTheme:string):Observable<ThemeModel>{
    return this.http.post<ThemeModel>('/api/theme/save',titleTheme,{headers:this.reqHeader});
  }

  getAllTemplateThemes():Observable<string[]>{
    return this.http.get<string[]>('/api/theme/template',{headers:this.reqHeader});
  }

  getUserPollThemes(idUser:number):Observable<string[]>{
    // const headers = new Headers({
    //   'Authorization': 'Bearer_'+localStorage.getItem('token')
    // });


    return this.http.get<string[]>('/api/theme/userId?userId='+idUser,{headers:this.reqHeader});
  }
}
