import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContaFiltroComponent } from './conta-filtro.component';

describe('ContaFiltroComponent', () => {
  let component: ContaFiltroComponent;
  let fixture: ComponentFixture<ContaFiltroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContaFiltroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContaFiltroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
