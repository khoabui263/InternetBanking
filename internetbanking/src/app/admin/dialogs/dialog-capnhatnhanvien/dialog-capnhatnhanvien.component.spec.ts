import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogCapnhatnhanvienComponent } from './dialog-capnhatnhanvien.component';

describe('DialogCapnhatnhanvienComponent', () => {
  let component: DialogCapnhatnhanvienComponent;
  let fixture: ComponentFixture<DialogCapnhatnhanvienComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogCapnhatnhanvienComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogCapnhatnhanvienComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
