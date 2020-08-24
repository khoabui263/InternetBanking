import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DatePipe } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { UsersComponent } from './users.component';
import { TaikhoanthanhtoanComponent } from './quanlytaikhoan/taikhoanthanhtoan/taikhoanthanhtoan.component';
import { MaterialModule } from '../material/material.module';
import { LichsugiaodichComponent } from './lichsugiaodich/lichsugiaodich.component';
import { ChuyenkhoanComponent } from './chuyenkhoan/chuyenkhoan.component';
import { MatPaginatorModule, MatProgressSpinnerModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
import { NgxCurrencyModule } from 'ngx-currency';
import { DoimatkhauComponent } from './quanlytaikhoan/doimatkhau/doimatkhau.component';
import { TaonhacnoComponent } from './quanlynhacno/taonhacno/taonhacno.component';
import { DanhsachnhacnoComponent } from './quanlynhacno/danhsachnhacno/danhsachnhacno.component';
import { TaotengoinhoComponent } from './quanlytengoinho/taotengoinho/taotengoinho.component';
import { DanhsachtengoinhoComponent } from './quanlytengoinho/danhsachtengoinho/danhsachtengoinho.component';


@NgModule({
  declarations: [
    UsersComponent,
    TaikhoanthanhtoanComponent,
    LichsugiaodichComponent,
    ChuyenkhoanComponent,
    DoimatkhauComponent,
    TaonhacnoComponent,
    DanhsachnhacnoComponent,
    TaotengoinhoComponent,
    DanhsachtengoinhoComponent,

  ],
  imports: [
    CommonModule,
    FormsModule,
    UsersRoutingModule,
    MaterialModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    NgxCurrencyModule,

  ],
  providers: [
    DatePipe
  ]
})
export class UsersModule { }
