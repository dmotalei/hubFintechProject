import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransacaoGridComponent } from './transacao-grid.component';

describe('TransacaoGridComponent', () => {
  let component: TransacaoGridComponent;
  let fixture: ComponentFixture<TransacaoGridComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransacaoGridComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransacaoGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
