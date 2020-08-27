import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogReminderComponent } from './dialog-reminder.component';

describe('DialogReminderComponent', () => {
  let component: DialogReminderComponent;
  let fixture: ComponentFixture<DialogReminderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogReminderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogReminderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
