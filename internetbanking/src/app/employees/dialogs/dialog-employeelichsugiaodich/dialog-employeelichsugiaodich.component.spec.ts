import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogEmployeelichsugiaodichComponent } from './dialog-employeelichsugiaodich.component';

describe('DialogEmployeelichsugiaodichComponent', () => {
  let component: DialogEmployeelichsugiaodichComponent;
  let fixture: ComponentFixture<DialogEmployeelichsugiaodichComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogEmployeelichsugiaodichComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogEmployeelichsugiaodichComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
