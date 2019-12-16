import {AnswerModel} from "./answer.model";

export class QuestionModel {
  public id: number;
  public textTitle: string;
  public type: string;
  public required: string;
  public idPoll: number;
  public answers: AnswerModel[];
  public user_answers: any;

  constructor() {
  }
}
