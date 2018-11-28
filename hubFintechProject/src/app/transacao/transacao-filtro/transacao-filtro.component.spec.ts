import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransacaoFiltroComponent } from './transacao-filtro.component';

describe('TransacaoFiltroComponent', () => {
  let component: TransacaoFiltroComponent;
  let fixture: ComponentFixture<TransacaoFiltroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransacaoFiltroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransacaoFiltroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
