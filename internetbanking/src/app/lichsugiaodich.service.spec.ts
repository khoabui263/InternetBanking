import { TestBed } from '@angular/core/testing';

import { LichsugiaodichService } from './lichsugiaodich.service';

describe('LichsugiaodichService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LichsugiaodichService = TestBed.get(LichsugiaodichService);
    expect(service).toBeTruthy();
  });
});
