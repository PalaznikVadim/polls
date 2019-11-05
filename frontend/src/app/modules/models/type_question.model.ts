export class TypeQuestionModel {
  private _id:number;
  private _type:string;

  constructor(){}


  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get type(): string {
    return this._type;
  }

  set type(value: string) {
    this._type = value;
  }
}
