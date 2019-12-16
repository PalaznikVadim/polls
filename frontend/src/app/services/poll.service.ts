import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PollModel} from "../modules/models/poll.model";
import {ClonePollModel} from "../modules/models/clonePoll.model";
import {RestPageModel} from "../modules/models/rest-page.model";


@Injectable()
export class PollService {
  private page: RestPageModel;
  private _currPoll: PollModel;

  get currPoll(): PollModel {
    return this._currPoll;
  }

  set currPoll(value: PollModel) {
    this._currPoll = value;
  }


  constructor(private http: HttpClient) {
  }

  getPollsPageByUserId(userId: number, select: string, theme: string, search: string,
                       page: number, size: number, sort: string, order: string): Observable<RestPageModel> {
    return this.http.get<RestPageModel>('/api/poll/user' + '?userId=' + userId + '&select=' + select + '&theme=' +
      theme + '&substr=' + search + '&page=' + page + '&size=' + size + '&sort=' + sort + '&order=' + order);
  }

  getPollById(id: string) {
    return this.http.get<PollModel>('/api/poll/' + id);
  }

  savePoll(poll: PollModel): Observable<PollModel> {
    return this.http.post<PollModel>('/api/poll', poll);
  }

  deletePoll(id: string): Observable<void> {
    return this.http.delete<void>('/api/poll/' + id);
  }

  getAllTemplatesByTheme(theme: string): Observable<PollModel[]> {
    return this.http.get<PollModel[]>('/api/poll/template?theme=' + theme);
  }

  clonePoll(clonePoll: ClonePollModel): Observable<PollModel> {
    return this.http.post<PollModel>('/api/poll/clone', clonePoll);
  }

  submitPoll(id: number): Observable<PollModel> {
    return this.http.post<PollModel>('api/poll/submit/' + id, id);
  }

  getPollByLink(pollLink: string): Observable<PollModel> {
    return this.http.get<PollModel>('api/poll?link=' + pollLink);

  }
}
