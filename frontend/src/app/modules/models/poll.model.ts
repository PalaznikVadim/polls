import {QuestionModel} from "./question.model";


export class PollModel {
  public name: string;
  public date: Date;
  public description: string;
  public theme:string;
  public quests:QuestionModel[];

  constructor() {
  }
}
