import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopsAroundYouComponent } from './shops-around-you.component';

describe('ShopsAroundYouComponent', () => {
  let component: ShopsAroundYouComponent;
  let fixture: ComponentFixture<ShopsAroundYouComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShopsAroundYouComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShopsAroundYouComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
