import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ThemeModel} from "../modules/models/theme.model";
import {observableToBeFn} from "rxjs/internal/testing/TestScheduler";


@Injectable()
export class ThemeService{

  constructor(private http: HttpClient) {
  }

  getAllTheme():Observable<ThemeModel[]>{
    return this.http.get<ThemeModel[]>('/api/theme');
  }

  getThemeByName(name:string):Observable<ThemeModel>{
    return this.http.get<ThemeModel>('/api/theme/name'+'?name='+name);
  }

  save(titleTheme:string):Observable<ThemeModel>{
    return this.http.post<ThemeModel>('/api/theme/save',titleTheme);
  }

}
