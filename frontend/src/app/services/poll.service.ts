
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PollModel} from "../modules/models/poll.model";
import {UserModel} from "../modules/models/user.model";
import {ThemeModel} from "../modules/models/theme.model";
import {ClonePollModel} from "../modules/models/clonePoll.model";

@Injectable()
// Data service
export class PollService {
  get currPoll(): PollModel {
    return this._currPoll;
  }

  set currPoll(value: PollModel) {
    this._currPoll = value;
  } //todo create interface

  private _currPoll:PollModel;

  constructor(private http: HttpClient) {
  }

  // Ajax request for billing account data
  getPollsByUserId(userId:number): Observable<PollModel[]> {
    return this.http.get<PollModel[]>('/api/poll/user'+'?userId='+userId);
  }

  getPollById(id:number){
    return this.http.get<PollModel>('/api/poll/id'+'?id='+id);
  }

  savePoll(poll:PollModel):Observable<PollModel>{
    return this.http.post<PollModel>('/api/poll',poll);
  }

  deletePoll(id: string): Observable<void> {
    return this.http.delete<void>('/api/poll/delete?id=' + id);
  }

  getAllTemplatesByTheme(theme:string):Observable<PollModel[]>{
    return this.http.get<PollModel[]>('/api/poll/template?theme='+theme);
  }

  clonePoll(clonePoll:ClonePollModel):Observable<PollModel>{
    return this.http.post<PollModel>('/api/poll/clone',clonePoll);
  }
}
