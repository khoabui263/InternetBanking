import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogActivegoinhoComponent } from './dialog-activegoinho.component';

describe('DialogActivegoinhoComponent', () => {
  let component: DialogActivegoinhoComponent;
  let fixture: ComponentFixture<DialogActivegoinhoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogActivegoinhoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogActivegoinhoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
