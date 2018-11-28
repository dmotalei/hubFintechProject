import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransacaoMainComponent } from './transacao-main.component';

describe('TransacaoMainComponent', () => {
  let component: TransacaoMainComponent;
  let fixture: ComponentFixture<TransacaoMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransacaoMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransacaoMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
