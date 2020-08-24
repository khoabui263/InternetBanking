import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AdminComponent } from './admin.component';
import { LichsugiaodichComponent } from './lichsugiaodich/lichsugiaodich.component';
import { QuanlynhanvienComponent } from './quanlynhanvien/quanlynhanvien.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {
    path: 'home',
    component: AdminComponent,
    children: [
      { path: '', redirectTo: 'history-transfer', pathMatch: 'full' },
      { path: 'history-transfer', component:  LichsugiaodichComponent},
      { path: 'user-manager', component:  QuanlynhanvienComponent},
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
