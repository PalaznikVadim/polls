import {StatsModel} from "./stats.model";

export class AnswerModel {

  public id:number;
  public text:string;
  public idQuestion:number;
  public stats:StatsModel;

  constructor() {
  }

  // get id(): number {
  //   return this._id;
  // }
  //
  // set id(value: number) {
  //   this._id = value;
  // }
  //
  // get text(): string {
  //   return this._text;
  // }
  //
  // set text(value: string) {
  //   this._text = value;
  // }
  //
  // get idQuestion(): number {
  //   return this._idQuestion;
  // }
  //
  // set idQuestion(value: number) {
  //   this._idQuestion = value;
  // }
}
