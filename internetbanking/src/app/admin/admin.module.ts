import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { LichsugiaodichComponent } from './lichsugiaodich/lichsugiaodich.component';
import { QuanlynhanvienComponent } from './quanlynhanvien/quanlynhanvien.component';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module';
import { MatPaginatorModule } from '@angular/material';
import { NgxCurrencyModule } from 'ngx-currency';
import { TaotaikhoannhanvienComponent } from './taotaikhoannhanvien/taotaikhoannhanvien.component';

@NgModule({
  declarations: [
    AdminComponent,
    LichsugiaodichComponent,
    QuanlynhanvienComponent,
    TaotaikhoannhanvienComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    MaterialModule,
    MatPaginatorModule,
    NgxCurrencyModule,
  ]
})
export class AdminModule { }
