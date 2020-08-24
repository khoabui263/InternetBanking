import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TaotaikhoanComponent } from './taotaikhoan.component';

describe('TaotaikhoanComponent', () => {
  let component: TaotaikhoanComponent;
  let fixture: ComponentFixture<TaotaikhoanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TaotaikhoanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TaotaikhoanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
