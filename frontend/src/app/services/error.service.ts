import {ErrorModel} from "../modules/models/error.model";

export class ErrorService {
  public errors:ErrorModel[];

  constructor(){}

  checkErrorsForField(field:string):boolean{
    for(let i=0;i<this.errors.length;i++) {
      if (this.errors[i].field == field)
        return true;
    }
    return false
  }

  outErrorsForField(fieldName:string):string[]{
    let errors:string[]=[];
    for(let i=0;i<this.errors.length;i++){
      if(this.errors[i].field==fieldName)
        errors.push(this.errors[i].defaultMessage);
    }
    return errors;
  }
}
