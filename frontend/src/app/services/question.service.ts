import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {QuestionModel} from "../modules/models/question.model";
import {HttpClient, HttpHeaders} from "@angular/common/http";


@Injectable()
export class QuestionService {

  private readonly reqHeader:HttpHeaders;

  constructor(private http:HttpClient){
    this.reqHeader=new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer_' + localStorage.getItem('token')
    });
  }

  getAllQuestionByPollId(idPoll:number):Observable<QuestionModel[]>{
    return this.http.get<QuestionModel[]>('/api/question/poll?idPoll='+idPoll,{headers:this.reqHeader});
  }

  saveQuestion(question:QuestionModel):Observable<QuestionModel>{
    return this.http.post<QuestionModel>('/api/question',question,{headers:this.reqHeader});
  }

  deleteQuestion(id: number):Observable<void> {
    return this.http.delete<void>('api/question/delete?id='+id,{headers:this.reqHeader});
  }
}
