import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DanhsachtengoinhoComponent } from './danhsachtengoinho.component';

describe('DanhsachtengoinhoComponent', () => {
  let component: DanhsachtengoinhoComponent;
  let fixture: ComponentFixture<DanhsachtengoinhoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DanhsachtengoinhoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DanhsachtengoinhoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
