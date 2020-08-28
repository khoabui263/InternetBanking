import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatDialog } from '@angular/material';
import { GoinhoService } from 'src/app/services/goinho.service';
import * as jwt_decode from 'jwt-decode';
import { WebStorageSerivce } from 'src/app/services/webstorage.service';
import { DialogUpdategoinhoComponent } from '../../dialogs/dialog-updategoinho/dialog-updategoinho.component';
import { DialogDeletegoinhoComponent } from '../../dialogs/dialog-deletegoinho/dialog-deletegoinho.component';

export interface PeriodicElement {
  mataikhoangoinho: number;
  hotennguoigoinho: string;
  bietdanhgoinho: string;
}


@Component({
  selector: 'app-danhsachtengoinho',
  templateUrl: './danhsachtengoinho.component.html',
  styleUrls: ['./danhsachtengoinho.component.scss']
})
export class DanhsachtengoinhoComponent implements OnInit {
  displayedColumns: string[] = ['mataikhoangoinho', 'hotennguoigoinho', 'bietdanhgoinho', 'chinhsua'];
  dataSource = new MatTableDataSource();
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(
    private goinhoService: GoinhoService,
    private webStorageSerivce: WebStorageSerivce,
    public matDialog: MatDialog
  ) {

  }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.getGoiNho();
  }

  getGoiNho() {
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    const value = {
      mataikhoancannho: decodeAT.mataikhoan
    }
    this.goinhoService.findByMataikhoancannhoAndTrangthai(value).subscribe((res: any) => {
      console.log(res);
      this.dataSource.data = res.danhSachGoiNho;
    })
  }

  update(ma: any, cannho: any, goinho: any, chuoigoinho: any, hoten: any, bietdanh: any, nganhang: any) {
    const updateDialog = this.matDialog.open(DialogUpdategoinhoComponent, {
      disableClose: true,
      height: '300px',
      width: '500px',
      data: {
        id: ma,
        mataikhoancannho: cannho,
        mataikhoangoinho: goinho,
        chuoimanguoigoinho: chuoigoinho,
        hotennguoigoinho: hoten,
        bietdanhgoinho: bietdanh,
        manganhang: nganhang
      }
    });
    updateDialog.afterClosed().subscribe(() => {
      this.getGoiNho();
    })
  }

  delete(ma: any) {
    const deleteDialog = this.matDialog.open(DialogDeletegoinhoComponent, {
      disableClose: true,
      height: '300px',
      width: '500px',
      data: {
        id: ma
      }
    });
    deleteDialog.afterClosed().subscribe(() => {
      this.getGoiNho();
    })
  }

}
