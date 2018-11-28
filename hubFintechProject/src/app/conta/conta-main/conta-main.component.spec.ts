import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContaMainComponent } from './conta-main.component';

describe('ContaMainComponent', () => {
  let component: ContaMainComponent;
  let fixture: ComponentFixture<ContaMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContaMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContaMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
