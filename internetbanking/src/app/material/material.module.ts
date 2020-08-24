import { NgModule } from '@angular/core';
import {
  MatButtonModule,
  MatTableModule,
  MatStepperModule,
  MatTabsModule,
  MatSelectModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatDialogModule,
  MatFormFieldModule,
  MatInputModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatMenuModule,
  MatExpansionModule

} from '@angular/material';
import { ReactiveFormsModule } from '@angular/forms';

const materialComponent = [
  MatButtonModule,
  MatTableModule,
  MatStepperModule,
  MatTabsModule,
  MatSelectModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatDialogModule,
  MatFormFieldModule,
  MatInputModule,
  MatDatepickerModule,
  MatNativeDateModule,
  ReactiveFormsModule,
  MatMenuModule,
  MatExpansionModule,
];

@NgModule({
  declarations: [],
  imports: [materialComponent],
  exports: [materialComponent]
})
export class MaterialModule { }
