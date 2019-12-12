import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ThemeModel} from "../modules/models/theme.model";


@Injectable()
export class ThemeService {

  constructor(private http: HttpClient) {
  }

  getAllTheme(): Observable<ThemeModel[]> {
    return this.http.get<ThemeModel[]>('/api/theme');
  }

  save(titleTheme: string): Observable<ThemeModel> {
    return this.http.post<ThemeModel>('/api/theme/save', titleTheme);
  }

  getAllTemplateThemes(): Observable<string[]> {
    return this.http.get<string[]>('/api/theme/template');
  }

  getUserPollThemes(idUser: number): Observable<string[]> {
    return this.http.get<string[]>('/api/theme/userId?userId=' + idUser);
  }
}
