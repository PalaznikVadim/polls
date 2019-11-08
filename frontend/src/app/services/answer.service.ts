import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AnswerModel} from "../modules/models/answer.model";


@Injectable()
export class AnswerService{
  constructor(private http: HttpClient) {
  }

  getAllAnswerByIdQuestion(idQuestion:number):Observable<AnswerModel[]>{
      return this.http.get<AnswerModel[]>('/api/answer/question?id='+idQuestion);
  }

  saveAnswer(answer:AnswerModel):Observable<AnswerModel>{
      return this.http.post<AnswerModel>('api/answer',answer);
  }

  deleteById(id:number): Observable<void>{
    return this.http.delete<void>('api/answer/delete?id='+id);
  }
}
