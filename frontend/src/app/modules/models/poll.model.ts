import {QuestionModel} from "./question.model";

export class PollModel {

  public id: number;
  public name: string;
  public idUser: number;
  public theme: string;
  public description: string;
  public link: string;
  public date: Date;
  public status: string;
  public shared: string;
  public questions: QuestionModel[];

  constructor() {
  }
}
