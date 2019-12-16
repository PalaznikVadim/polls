import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {QuestionModel} from "../modules/models/question.model";
import {HttpClient} from "@angular/common/http";


@Injectable()
export class QuestionService {

  constructor(private http: HttpClient) {
  }

  getAllQuestionByPollId(idPoll: number): Observable<QuestionModel[]> {
    return this.http.get<QuestionModel[]>('/api/question/poll/' + idPoll);
  }

  saveQuestion(question: QuestionModel): Observable<QuestionModel> {
    return this.http.post<QuestionModel>('/api/question', question);
  }

  deleteQuestion(id: number): Observable<void> {
    return this.http.delete<void>('/api/question/' + id);
  }
}
