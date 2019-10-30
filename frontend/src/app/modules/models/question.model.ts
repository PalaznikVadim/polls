export class QuestionModel{
  public id:number;
  public title:string;
  public type:string;
  public answers:string[];
  public user_answer:any[];
  public required:boolean;

  constructor(){}
}
