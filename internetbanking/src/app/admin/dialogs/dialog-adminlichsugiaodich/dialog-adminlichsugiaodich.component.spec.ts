import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogAdminlichsugiaodichComponent } from './dialog-adminlichsugiaodich.component';

describe('DialogAdminlichsugiaodichComponent', () => {
  let component: DialogAdminlichsugiaodichComponent;
  let fixture: ComponentFixture<DialogAdminlichsugiaodichComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogAdminlichsugiaodichComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogAdminlichsugiaodichComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
