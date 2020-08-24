import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DanhsachnhacnoComponent } from './danhsachnhacno.component';

describe('DanhsachnhacnoComponent', () => {
  let component: DanhsachnhacnoComponent;
  let fixture: ComponentFixture<DanhsachnhacnoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DanhsachnhacnoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DanhsachnhacnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
