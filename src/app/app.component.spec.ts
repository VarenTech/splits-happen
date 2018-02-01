import {TestBed, async, ComponentFixture} from '@angular/core/testing';
import { AppComponent } from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MaterialModule} from '../assets/material/material.module';
describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
     imports:[
      MaterialModule,
      FormsModule,
      ReactiveFormsModule,
      BrowserAnimationsModule
       ],
      declarations: [
        AppComponent
      ],
    }).compileComponents();
  }));
  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.debugElement.componentInstance;
    fixture.detectChanges();
  });

  it('should create the app', async(() => {
    expect(component).toBeTruthy();
  }));

  it(`should have as title 'bowling score generator'`, async(() => {
    expect(component.title).toEqual('bowling score generator');
  }));

  it('should have all strikes as first test', async( () => {
    expect(component.TESTS[0]).toEqual('XXXXXXXXXXXX');
  }));

  it('should score 300', async( () => {
    expect(component.scoreGame(component.TESTS[0])).toEqual(300);
  }));

  it('should score 90', async( () => {
    expect(component.scoreGame(component.TESTS[1])).toEqual(90);
  }));

  it('should score 300', async( () => {
    expect(component.scoreGame(component.TESTS[2])).toEqual(150);
  }));

  it('should score 300', async( () => {
    expect(component.scoreGame(component.TESTS[3])).toEqual(167);
  }));

  it('should replace `X` with 10', async( () => {
    expect(component.adjustStrikes([['X']])).toEqual([[10]]);
  }));


});
