import { TestBed, inject } from '@angular/core/testing';

import { ModalPessoaService } from './modal-pessoa.service';

describe('ModalPessoaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ModalPessoaService]
    });
  });

  it('should be created', inject([ModalPessoaService], (service: ModalPessoaService) => {
    expect(service).toBeTruthy();
  }));
});
