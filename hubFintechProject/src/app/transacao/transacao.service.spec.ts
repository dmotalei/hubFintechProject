import { TestBed, inject } from '@angular/core/testing';

import { TransacaoService } from './transacao.service';

describe('TransacaoService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TransacaoService]
    });
  });

  it('should be created', inject([TransacaoService], (service: TransacaoService) => {
    expect(service).toBeTruthy();
  }));
});
