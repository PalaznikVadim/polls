import {QuestionModel} from "./question.model";

export class PollModel {

  public id:number;
  public name: string;
  public idUser:number;
  public idTheme:number;
  public description:string;
  public link:string;
  public dataTime:Date;
  public status:string;
  public shared:string;
  public questions:QuestionModel[];

  constructor() {}


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
  //
  // get idUser(): number {
  //   return this._idUser;
  // }
  //
  // set idUser(value: number) {
  //   this._idUser = value;
  // }
  //
  // get idTheme(): number {
  //   return this._idTheme;
  // }
  //
  // set idTheme(value: number) {
  //   this._idTheme = value;
  // }
  //
  // get description(): string {
  //   return this._description;
  // }
  //
  // set description(value: string) {
  //   this._description = value;
  // }
  //
  // get link(): string {
  //   return this._link;
  // }
  //
  // set link(value: string) {
  //   this._link = value;
  // }
  //
  // get dateAdding(): Date {
  //   return this._dateAdding;
  // }
  //
  // set dateAdding(value: Date) {
  //   this._dateAdding = value;
  // }
  //
  // get status(): string {
  //   return this._status;
  // }
  //
  // set status(value: string) {
  //   this._status = value;
  // }
  //
  // get shared(): string {
  //   return this._shared;
  // }
  //
  // set shared(value: string) {
  //   this._shared = value;
  // }
}
