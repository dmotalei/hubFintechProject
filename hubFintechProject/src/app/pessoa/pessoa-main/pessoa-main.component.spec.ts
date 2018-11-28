import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PessoaMainComponent } from './pessoa-main.component';

describe('PessoaMainComponent', () => {
  let component: PessoaMainComponent;
  let fixture: ComponentFixture<PessoaMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PessoaMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PessoaMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
