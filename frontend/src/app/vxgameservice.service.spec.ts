import { TestBed, inject } from '@angular/core/testing';

import { VxgameserviceService } from './vxgameservice.service';

describe('VxgameserviceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [VxgameserviceService]
    });
  });

  it('should be created', inject([VxgameserviceService], (service: VxgameserviceService) => {
    expect(service).toBeTruthy();
  }));
});
