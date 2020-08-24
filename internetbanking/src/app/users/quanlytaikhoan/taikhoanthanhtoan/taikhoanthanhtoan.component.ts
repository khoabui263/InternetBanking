import { Component, OnInit, ViewChild, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { MatTableDataSource, MatPaginator } from '@angular/material';
import { TaiKhoanDangNhapService } from 'src/app/services/taikhoandangnhap.service';

export interface PhanTu {
  hoten: string;
  email: string;
  sodienthoai: number;
  tenloainguoidung: string;
}

@Component({
  selector: 'app-taikhoanthanhtoan',
  templateUrl: './taikhoanthanhtoan.component.html',
  styleUrls: ['./taikhoanthanhtoan.component.scss']
})

export class TaikhoanthanhtoanComponent implements OnInit {
  displayedColumns: string[] = ['hoten', 'email', 'sodienthoai', 'tenloainguoidung'];
  dataSource = new MatTableDataSource();
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(
    private taiKhoanDangNhapService: TaiKhoanDangNhapService,
    private changeDetectorRefs: ChangeDetectorRef
  ) { }

  ngOnInit() {
    this.getAccount();
    this.dataSource.paginator = this.paginator;
  }

  getAccount() {
    this.taiKhoanDangNhapService.findAllDetailsTaiKhoanDangNhap().subscribe((res: any) => {
      console.log(res.DanhSachTaiKhoan);
      this.dataSource.data = res.DanhSachTaiKhoan;
    })
  }

  /**
   * Tim kiem tu dong ko can button, click vao danh sach goi y se load len tai khoan can tim
   */

}
