import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DialogUpdategoinhoComponent } from './dialog-updategoinho.component';

describe('DialogUpdategoinhoComponent', () => {
  let component: DialogUpdategoinhoComponent;
  let fixture: ComponentFixture<DialogUpdategoinhoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DialogUpdategoinhoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DialogUpdategoinhoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
