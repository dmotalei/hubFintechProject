import { TestBed, inject } from '@angular/core/testing';

import { ModalContaService } from './modal-conta.service';

describe('ModalContaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ModalContaService]
    });
  });

  it('should be created', inject([ModalContaService], (service: ModalContaService) => {
    expect(service).toBeTruthy();
  }));
});
