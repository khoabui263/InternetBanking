import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaonhacnoComponent } from './taonhacno.component';

describe('TaonhacnoComponent', () => {
  let component: TaonhacnoComponent;
  let fixture: ComponentFixture<TaonhacnoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaonhacnoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaonhacnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
