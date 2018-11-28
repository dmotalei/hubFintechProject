import { TestBed, inject } from '@angular/core/testing';

import { ModalTransacaoService } from './modal-transacao.service';

describe('ModalTransacaoService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ModalTransacaoService]
    });
  });

  it('should be created', inject([ModalTransacaoService], (service: ModalTransacaoService) => {
    expect(service).toBeTruthy();
  }));
});
