import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";


@Injectable()
export class AnswerService {
  constructor(private http: HttpClient) {
  }

  deleteById(id: number): Observable<void> {
    return this.http.delete<void>('api/answer/' + id);
  }
}
