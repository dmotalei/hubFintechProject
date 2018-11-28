import { TransacaoModule } from './transacao.module';

describe('TransacaoModule', () => {
  let transacaoModule: TransacaoModule;

  beforeEach(() => {
    transacaoModule = new TransacaoModule();
  });

  it('should create an instance', () => {
    expect(transacaoModule).toBeTruthy();
  });
});
