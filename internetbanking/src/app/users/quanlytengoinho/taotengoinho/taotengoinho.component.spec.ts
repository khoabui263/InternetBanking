import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaotengoinhoComponent } from './taotengoinho.component';

describe('TaotengoinhoComponent', () => {
  let component: TaotengoinhoComponent;
  let fixture: ComponentFixture<TaotengoinhoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaotengoinhoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaotengoinhoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
