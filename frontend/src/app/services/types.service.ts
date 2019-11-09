import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TypeQuestionModel} from "../modules/models/type_question.model";

@Injectable()
export class TypesService {
  public types:TypeQuestionModel[];

  constructor(private http: HttpClient) {}

  getAllTypes():Observable<TypeQuestionModel[]>{
    return this.http.get<TypeQuestionModel[]>('/api/type')
  }

  findDescriptionTypeById(id:number):string{
    if(this.types!=null)
      for(let i=0;i<this.types.length;i++)
        if(id==this.types[i].id)
          return this.types[i].description;
  }

  findIdTypeByDescription(typeQuestion:string):number{
    if(this.types!=null)
      for(let i=0;i<this.types.length;i++)
        if(typeQuestion==this.types[i].description)
          return this.types[i].id;


  }

  findTypeById(id:number):string{
    if(this.types!=null)
      for(let i=0;i<this.types.length;i++)
        if(id==this.types[i].id)
          return this.types[i].type;
  }
}
