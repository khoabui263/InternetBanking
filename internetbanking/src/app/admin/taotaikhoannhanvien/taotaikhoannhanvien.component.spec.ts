import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaotaikhoannhanvienComponent } from './taotaikhoannhanvien.component';

describe('TaotaikhoannhanvienComponent', () => {
  let component: TaotaikhoannhanvienComponent;
  let fixture: ComponentFixture<TaotaikhoannhanvienComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaotaikhoannhanvienComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaotaikhoannhanvienComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
