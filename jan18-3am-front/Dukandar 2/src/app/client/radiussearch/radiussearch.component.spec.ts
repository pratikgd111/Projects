import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RadiussearchComponent } from './radiussearch.component';

describe('RadiussearchComponent', () => {
  let component: RadiussearchComponent;
  let fixture: ComponentFixture<RadiussearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RadiussearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RadiussearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
