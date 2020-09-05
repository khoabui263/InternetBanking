import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogXoanhanvienComponent } from './dialog-xoanhanvien.component';

describe('DialogXoanhanvienComponent', () => {
  let component: DialogXoanhanvienComponent;
  let fixture: ComponentFixture<DialogXoanhanvienComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogXoanhanvienComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogXoanhanvienComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
