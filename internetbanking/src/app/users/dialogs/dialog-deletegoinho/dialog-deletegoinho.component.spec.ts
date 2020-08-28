import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogDeletegoinhoComponent } from './dialog-deletegoinho.component';

describe('DialogDeletegoinhoComponent', () => {
  let component: DialogDeletegoinhoComponent;
  let fixture: ComponentFixture<DialogDeletegoinhoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogDeletegoinhoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogDeletegoinhoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
