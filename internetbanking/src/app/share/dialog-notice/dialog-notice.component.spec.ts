import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogNoticeComponent } from './dialog-notice.component';

describe('DialogNoticeComponent', () => {
  let component: DialogNoticeComponent;
  let fixture: ComponentFixture<DialogNoticeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogNoticeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogNoticeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
