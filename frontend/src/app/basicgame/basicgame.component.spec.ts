import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BasicgameComponent } from './basicgame.component';

describe('BasicgameComponent', () => {
  let component: BasicgameComponent;
  let fixture: ComponentFixture<BasicgameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BasicgameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BasicgameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
