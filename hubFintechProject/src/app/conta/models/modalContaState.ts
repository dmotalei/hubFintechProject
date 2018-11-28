import { Conta } from "src/app/conta/models/conta.model";
import { StatusConta } from "./statusconta.model";
import { Pessoa } from "src/app/pessoa/models/pessoa.model";


export class ModalContaSearchState{
    exibir: boolean = false;
    error: object = null;
    conta: Conta = null;
    statusList: StatusConta[] = [];
  }