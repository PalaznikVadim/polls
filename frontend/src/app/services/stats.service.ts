import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {QuestionModel} from "../modules/models/question.model";

@Injectable()
export class StatsService{
  constructor(private http:HttpClient){}

  public getPollStats(idPoll:number):Observable<QuestionModel[]>{
    return this.http.get<QuestionModel[]>("/api/stats?idPoll="+idPoll);
  }
}
