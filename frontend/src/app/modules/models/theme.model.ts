import {PollModel} from "./poll.model";

export class ThemeModel {
  public id:number;
  public name:string;
  public polls:PollModel[];

  constructor(){}


  // get id(): number {
  //   return this._id;
  // }
  //
  // set id(value: number) {
  //   this._id = value;
  // }
  //
  // get name(): string {
  //   return this._name;
  // }
  //
  // set name(value: string) {
  //   this._name = value;
  // }
}
