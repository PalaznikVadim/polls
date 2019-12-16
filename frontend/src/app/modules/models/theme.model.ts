import {PollModel} from "./poll.model";

export class ThemeModel {
  public id: number;
  public name: string;
  public polls: PollModel[];

  constructor() {
  }

}
