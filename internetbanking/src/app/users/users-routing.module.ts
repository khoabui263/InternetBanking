import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { UsersComponent } from './users.component';
import { TaikhoanthanhtoanComponent } from './quanlytaikhoan/taikhoanthanhtoan/taikhoanthanhtoan.component';
import { LichsugiaodichComponent } from './lichsugiaodich/lichsugiaodich.component';
import { ChuyenkhoanComponent } from './chuyenkhoan/chuyenkhoan.component';
import { DoimatkhauComponent } from './quanlytaikhoan/doimatkhau/doimatkhau.component';
import { TaonhacnoComponent } from './quanlynhacno/taonhacno/taonhacno.component';
import { DanhsachnhacnoComponent } from './quanlynhacno/danhsachnhacno/danhsachnhacno.component';
import { TaotengoinhoComponent } from './quanlytengoinho/taotengoinho/taotengoinho.component';
import { DanhsachtengoinhoComponent } from './quanlytengoinho/danhsachtengoinho/danhsachtengoinho.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {
    path: 'home',
    component: UsersComponent,
    children: [
      { path: '', redirectTo: 'payment-account', pathMatch: 'full' },
      { path: 'payment-account', component: TaikhoanthanhtoanComponent },
      { path: 'change-password', component: DoimatkhauComponent },
      { path: 'transfer', component: ChuyenkhoanComponent },
      { path: 'history-transfer', component: LichsugiaodichComponent },
      { path: 'create-debt', component: TaonhacnoComponent },
      { path: 'list-debt', component: DanhsachnhacnoComponent },
      { path: 'create-reminder', component: TaotengoinhoComponent },
      { path: 'list-reminder', component: DanhsachtengoinhoComponent },
      // { path: 'reminder-manager', component: QuanlytengoinhoComponent },


    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
