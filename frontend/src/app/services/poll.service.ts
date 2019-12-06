import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, pipe} from "rxjs";
import {PollModel} from "../modules/models/poll.model";
import {ClonePollModel} from "../modules/models/clonePoll.model";
import {RestPageModel} from "../modules/models/rest-page.model";


@Injectable()
// Data service
export class PollService {
  private page: RestPageModel;
  private _currPoll: PollModel;
  private readonly reqHeader:HttpHeaders;

  get currPoll(): PollModel {
    return this._currPoll;
  }

  set currPoll(value: PollModel) {
    this._currPoll = value;
  } //todo create interface


  constructor(private http: HttpClient) {
    console.log(localStorage.getItem('token'));
    console.log(localStorage.getItem('token').toString());
    this.reqHeader=new HttpHeaders({
      // 'Content-Type': 'application/json',
      'Authorization': 'Bearer_' + localStorage.getItem('token')
    });
    console.log(this.reqHeader);
  }

  // Ajax request for billing account data
  getPollsPageByUserId(userId: number, select: string,search:string,
                   page: number, size: number, sort: string, order: string): Observable<RestPageModel> {
    console.log(this.reqHeader);
    return this.http.get<RestPageModel>('/api/poll/user' + '?userId=' + userId + '&select=' + select +
      '&substr='+search+'&page=' + page + '&size=' + size + '&sort=' + sort + '&order=' + order,{headers:this.reqHeader});
  }

  getPollById(id: string) {
    return this.http.get<PollModel>('/api/poll/id' + '?id=' + id,{headers:this.reqHeader});
  }

  savePoll(poll: PollModel): Observable<PollModel> {
    return this.http.post<PollModel>('/api/poll', poll,{headers:this.reqHeader});
  }

  deletePoll(id: string): Observable<void> {
    return this.http.delete<void>('/api/poll/delete?id=' + id,{headers:this.reqHeader});
  }

  getAllTemplatesByTheme(theme: string): Observable<PollModel[]> {
    return this.http.get<PollModel[]>('/api/poll/template?theme=' + theme,{headers:this.reqHeader});
  }

  clonePoll(clonePoll: ClonePollModel): Observable<PollModel> {
    return this.http.post<PollModel>('/api/poll/clone', clonePoll,{headers:this.reqHeader});
  }

  submitPoll(id: number): Observable<PollModel> {
    return this.http.post<PollModel>('api/poll/submit/' + id, id,{headers:this.reqHeader});
  }

  getPollByLink(pollLink: string): Observable<PollModel> {
    return this.http.get<PollModel>('api/poll?link=' + pollLink,);

  }
}
