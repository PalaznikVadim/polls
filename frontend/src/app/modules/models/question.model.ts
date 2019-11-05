export class QuestionModel{
  private _id:number;
  private _textTitle:string;
  private _idType:number;
  private _required:string;
  private _idPoll:number;

  constructor(){}


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get textTitle(): string {
    return this._textTitle;
  }

  set textTitle(value: string) {
    this._textTitle = value;
  }

  get idType(): number {
    return this._idType;
  }

  set idType(value: number) {
    this._idType = value;
  }

  get required(): string {
    return this._required;
  }

  set required(value: string) {
    this._required = value;
  }

  get idPoll(): number {
    return this._idPoll;
  }

  set idPoll(value: number) {
    this._idPoll = value;
  }
}
