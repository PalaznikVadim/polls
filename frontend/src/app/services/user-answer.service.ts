import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {UserAnswerModel} from "../modules/models/user-answer.model";

import {Observable} from "rxjs";


@Injectable()
export class UserAnswerService {
  constructor(private http:HttpClient){}

  saveAllUserAnswer(userAnswers:UserAnswerModel[]):Observable<UserAnswerModel[]>{
    return this.http.post<UserAnswerModel[]>('/api/userAnswer/all',userAnswers);
  }
}
