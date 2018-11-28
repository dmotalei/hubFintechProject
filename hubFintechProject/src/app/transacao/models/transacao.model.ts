import { Conta } from "src/app/conta/models/conta.model";


export class Transacao{

    id: number;
    contaDestino:Conta;
    contaRemetente: Conta;
    valor: number;
    flagAporte: boolean;
    dataTransacao: Date;
    dataEstorno: Date
    codigoEstornoAporte: string;
}