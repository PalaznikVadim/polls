import {PollModel} from "./poll.model";

export class UserModel {
  public id: number;
  public name: string;
  public surname: string;
  public role: string;
  public dateOfBirth: Date;
  public email: string;
  public password: string;
  public polls: PollModel[];

  constructor() {
  }
}
