export class AnswerModel {

  private _id:number;
  private _text:string;
  private _idQuestion:number;


  constructor() {
  }


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get text(): string {
    return this._text;
  }

  set text(value: string) {
    this._text = value;
  }

  get idQuestion(): number {
    return this._idQuestion;
  }

  set idQuestion(value: number) {
    this._idQuestion = value;
  }
}
