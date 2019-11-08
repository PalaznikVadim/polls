import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TypeQuestionModel} from "../modules/models/type_question.model";

@Injectable()
export class TypesService {
  constructor(private http: HttpClient) {}

  getAllTypes():Observable<TypeQuestionModel[]>{
    return this.http.get<TypeQuestionModel[]>('/api/type')
  }

}
