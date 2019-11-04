import {QuestionModel} from "./question.model";
import {ThemeModel} from "./theme.model";


export class PollModel {
  public id:number;
  public title: string;
  public date: Date;
  public description: string;
  public theme:ThemeModel;
  public quests:QuestionModel[];

  constructor() {
  }
}
