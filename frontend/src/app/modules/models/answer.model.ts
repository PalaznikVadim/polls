import {StatsModel} from "./stats.model";

export class AnswerModel {
  public id:number;
  public text:string;
  public idQuestion:number;
  public stats:StatsModel;

  constructor() {
  }
}
