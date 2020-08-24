import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NaptienvaotaikhoanComponent } from './naptienvaotaikhoan.component';

describe('NaptienvaotaikhoanComponent', () => {
  let component: NaptienvaotaikhoanComponent;
  let fixture: ComponentFixture<NaptienvaotaikhoanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NaptienvaotaikhoanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NaptienvaotaikhoanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
