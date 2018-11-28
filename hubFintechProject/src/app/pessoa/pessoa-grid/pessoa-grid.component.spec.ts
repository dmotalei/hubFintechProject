import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PessoaGridComponent } from './pessoa-grid.component';

describe('PessoaGridComponent', () => {
  let component: PessoaGridComponent;
  let fixture: ComponentFixture<PessoaGridComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PessoaGridComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PessoaGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
