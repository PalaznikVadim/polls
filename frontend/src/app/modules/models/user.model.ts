import {PollModel} from "./poll.model";

export class UserModel {

    public id:number;
    public name:string;
    public surname:string;
    public role:string;
    public dateOfBirth:Date;
    public email:string;
    public password:string;
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
  //
  // get surname(): string {
  //   return this._surname;
  // }
  //
  // set surname(value: string) {
  //   this._surname = value;
  // }
  //
  // get role(): string {
  //   return this._role;
  // }
  //
  // set role(value: string) {
  //   this._role = value;
  // }
  //
  // get dateOfBirth(): Date {
  //   return this._dateOfBirth;
  // }
  //
  // set dateOfBirth(value: Date) {
  //   this._dateOfBirth = value;
  // }
  //
  // get email(): string {
  //   return this._email;
  // }
  //
  // set email(value: string) {
  //   this._email = value;
  // }
  //
  // get password(): string {
  //   return this._password;
  // }
  //
  // set password(value: string) {
  //   this._password = value;
  // }
}
