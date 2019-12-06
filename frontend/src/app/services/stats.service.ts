import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {QuestionModel} from "../modules/models/question.model";

@Injectable()
export class StatsService{
  private readonly reqHeader:HttpHeaders;
  constructor(private http:HttpClient) {
    this.reqHeader = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer_' + localStorage.getItem('token')
    });
  }


  public getPollStats(idPoll:number):Observable<QuestionModel[]>{
    return this.http.get<QuestionModel[]>("/api/stats?idPoll="+idPoll,{headers:this.reqHeader});
  }
}
