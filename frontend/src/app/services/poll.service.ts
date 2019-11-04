
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PollModel} from "../modules/models/poll.model";
import {UserModel} from "../modules/models/user.model";

@Injectable()
// Data service
export class PollService { //todo create interface

  constructor(private http: HttpClient) {
  }

  // Ajax request for billing account data
  getPollsByUserId(userId:number): Observable<PollModel[]> {
    return this.http.get<PollModel[]>('/api/poll'+'?userId='+userId);
  }

  getPollById(id:number){
    return this.http.get<PollModel>('/api/poll/id'+'?id='+id);
  }

  savePoll(poll:PollModel):Observable<PollModel>{
    return this.http.post<PollModel>('/api/poll',poll);
  }
}
/*
  saveBillingAccount(billingAccount: BillingAccount): Observable<BillingAccount> {
    return this.http.post<BillingAccount>('/api/ba', billingAccount);
  }

  deleteBillingAccount(billingAccountId: string): Observable<void> {
    return this.http.delete<void>('/api/ba/' + billingAccountId);
  }

  getBillingAccountById(id: string): Observable<BillingAccount> {
    return this.http.get<BillingAccount>('/api/ba/' + id);
  }

}


 */
