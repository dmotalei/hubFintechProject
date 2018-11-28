import { Pessoa } from "src/app/pessoa/models/pessoa.model";
import { StatusConta } from "./statusconta.model";


export class Conta{
    id: number;
    numeroConta:string;
    pessoa: Pessoa;
    contaPai: Conta;
    saldo: number;
    statusConta: StatusConta;
}