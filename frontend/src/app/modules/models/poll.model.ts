import {QuestionModel} from "./question.model";


export class PollModel {
  public id:number;
  public title: string;
  public dateAdding: Date;
  public description: string;
  public theme:string;
  public quests:QuestionModel[];

  constructor() {
  }
}
