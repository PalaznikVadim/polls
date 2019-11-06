import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {QuestionModel} from "../modules/models/question.model";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class QuestionService {

  constructor(private http:HttpClient){}

  getAllQuestionByPollId(idPoll:number):Observable<QuestionModel[]>{
    return this.http.get<QuestionModel[]>('/api/question/idPoll?idPoll='+idPoll);

  }
}
