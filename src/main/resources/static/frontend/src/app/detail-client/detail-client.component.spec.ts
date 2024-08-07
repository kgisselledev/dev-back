import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailClientComponent } from './detail-client.component';

describe('DetailClientComponent', () => {
  let component: DetailClientComponent;
  let fixture: ComponentFixture<DetailClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailClientComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
