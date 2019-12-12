import {ErrorModel} from "../modules/models/error.model";

export class ErrorService {


  constructor() {
  }

  checkErrorsForField(field:string,errors:ErrorModel[]):boolean{
    for(let i=0;i<errors.length;i++) {
      if (errors[i].field == field)
        return true;
    }
    return false
  }

  outErrorsForField(fieldName:string,errorsMass:ErrorModel[]):string[]{
    let errors:string[]=[];
    for(let i=0;i<errorsMass.length;i++){
      if(errorsMass[i].field==fieldName)
        errors.push(errorsMass[i].defaultMessage);
    }
    return errors;
  }
}
