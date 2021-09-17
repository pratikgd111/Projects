import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShopPhotoComponent } from './shop-photo.component';

describe('ShopPhotoComponent', () => {
  let component: ShopPhotoComponent;
  let fixture: ComponentFixture<ShopPhotoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShopPhotoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShopPhotoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
