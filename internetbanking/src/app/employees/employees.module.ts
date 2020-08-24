import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';

import { EmployeesRoutingModule } from './employees-routing.module';
import { EmployeesComponent } from './employees.component';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module';
import { MatPaginatorModule } from '@angular/material';
import { NgxCurrencyModule } from 'ngx-currency';
import { TaotaikhoanComponent } from './quanlykhachhang/taotaikhoan/taotaikhoan.component';
import { NaptienvaotaikhoanComponent } from './quanlykhachhang/naptienvaotaikhoan/naptienvaotaikhoan.component';
import { LichsugiaodichComponent } from './quanlykhachhang/lichsugiaodich/lichsugiaodich.component';


@NgModule({
  declarations: [
    EmployeesComponent,
    LichsugiaodichComponent,
    TaotaikhoanComponent,
    NaptienvaotaikhoanComponent
  ],
  imports: [
    CommonModule,
    EmployeesRoutingModule,
    FormsModule,
    MaterialModule,
    MatPaginatorModule,
    NgxCurrencyModule,
  ],
  providers: [
    DatePipe
  ]
})
export class EmployeesModule { }
