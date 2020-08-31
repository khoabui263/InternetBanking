import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogUpdatenhacnoComponent } from './dialog-updatenhacno.component';

describe('DialogUpdatenhacnoComponent', () => {
  let component: DialogUpdatenhacnoComponent;
  let fixture: ComponentFixture<DialogUpdatenhacnoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogUpdatenhacnoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogUpdatenhacnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
