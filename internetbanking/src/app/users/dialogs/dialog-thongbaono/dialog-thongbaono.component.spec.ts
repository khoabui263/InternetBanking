import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogThongbaonoComponent } from './dialog-thongbaono.component';

describe('DialogThongbaonoComponent', () => {
  let component: DialogThongbaonoComponent;
  let fixture: ComponentFixture<DialogThongbaonoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogThongbaonoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogThongbaonoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
