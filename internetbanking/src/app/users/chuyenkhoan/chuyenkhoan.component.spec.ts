import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChuyenkhoanComponent } from './chuyenkhoan.component';

describe('ChuyenkhoanComponent', () => {
  let component: ChuyenkhoanComponent;
  let fixture: ComponentFixture<ChuyenkhoanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChuyenkhoanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChuyenkhoanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
