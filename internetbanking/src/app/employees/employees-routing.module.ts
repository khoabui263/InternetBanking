import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EmployeesComponent } from './employees.component';
import { TaotaikhoanComponent } from './quanlykhachhang/taotaikhoan/taotaikhoan.component';
import { LichsugiaodichComponent } from './quanlykhachhang/lichsugiaodich/lichsugiaodich.component';
import { NaptienvaotaikhoanComponent } from './quanlykhachhang/naptienvaotaikhoan/naptienvaotaikhoan.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {
    path: 'home',
    component: EmployeesComponent,
    children: [
      { path: '', redirectTo: 'create-account', pathMatch: 'full' },
      { path: 'create-account', component:  TaotaikhoanComponent},
      { path: 'history-transfer', component: LichsugiaodichComponent },
      { path: 'recharge', component: NaptienvaotaikhoanComponent },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeesRoutingModule { }
