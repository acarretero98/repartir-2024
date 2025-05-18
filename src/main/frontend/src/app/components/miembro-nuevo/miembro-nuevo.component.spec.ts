import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MiembroNuevoComponent } from './miembro-nuevo.component';

describe('GastoNuevoComponent', () => {
  let component: MiembroNuevoComponent;
  let fixture: ComponentFixture<MiembroNuevoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MiembroNuevoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MiembroNuevoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
