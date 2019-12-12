import {AnswerModel} from "./answer.model";

export class QuestionModel{
  public id:number;
  public textTitle:string;
  public type:string;
  public required:string;
  public idPoll:number;
  public answers:AnswerModel[];
  public user_answers:any;

  constructor(){}


  // get id(): number {
  //   return this._id;
  // }
  //
  // set id(value: number) {
  //   this._id = value;
  // }
  //
  // get textTitle(): string {
  //   return this._textTitle;
  // }
  //
  // set textTitle(value: string) {
  //   this._textTitle = value;
  // }
  //
  // get idType(): number {
  //   return this._idType;
  // }
  //
  // set idType(value: number) {
  //   this._idType = value;
  // }
  //
  // get required(): string {
  //   return this._required;
  // }
  //
  // set required(value: string) {
  //   this._required = value;
  // }
  //
  // get idPoll(): number {
  //   return this._idPoll;
  // }
  //
  // set idPoll(value: number) {
  //   this._idPoll = value;
  // }
}
