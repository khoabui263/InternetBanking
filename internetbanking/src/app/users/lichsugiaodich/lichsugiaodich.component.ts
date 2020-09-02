import { Component, OnInit, ViewChild, ViewChildren, QueryList } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { DatePipe } from '@angular/common';
import { WebStorageSerivce } from 'src/app/services/webstorage.service';
import { MatDialog } from '@angular/material';
import { LichsugiaodichService } from 'src/app/services/lichsugiaodich.service';
import * as jwt_decode from 'jwt-decode';
import { DialogErrorsComponent } from 'src/app/share/dialog-errors/dialog-errors.component';

@Component({
  selector: 'app-lichsugiaodich',
  templateUrl: './lichsugiaodich.component.html',
  styleUrls: ['./lichsugiaodich.component.scss']
})
export class LichsugiaodichComponent implements OnInit {
  soLuongChuyenTien = 0;
  soLuongNhanTien = 0;
  soLuongMinhTraNo = 0;
  soLuongNguoiKhacTraNo = 0;

  displayedColumnsChuyenTien: string[] = ['mataikhoannguoinhan', 'tennguoinhan', 'sotiengiaodich', 'noidungchuyenkhoan', 'ngaygiaodich'];
  displayedColumnsNhanTien: string[] = ['mataikhoannguoigui', 'tennguoigui', 'sotiengiaodich', 'noidungchuyenkhoan', 'ngaygiaodich'];
  displayedColumnsTraNo: string[] = ['mataikhoannguoinhan', 'tennguoinhan', 'sotiengiaodich', 'noidungchuyenkhoan', 'ngaygiaodich'];
  displayedColumnsNguoiKhacTraNo: string[] = ['mataikhoannguoigui', 'tennguoigui', 'sotiengiaodich', 'noidungchuyenkhoan', 'ngaygiaodich'];

  dataSourceChuyenTien = new MatTableDataSource();
  dataSourceNhanTien = new MatTableDataSource();
  dataSourceTraNo = new MatTableDataSource();
  dataSourceNguoiKhacTraNo = new MatTableDataSource();

  startChuyenTien = new Date(new Date().setDate(new Date().getDate() - 30));
  startNhanTien = new Date(new Date().setDate(new Date().getDate() - 30));
  startTraNo = new Date(new Date().setDate(new Date().getDate() - 30));
  startNguoiKhacTraNo = new Date(new Date().setDate(new Date().getDate() - 30));

  endChuyenTien = new Date();
  endNhanTien = new Date();
  endTraNo = new Date();
  endNguoiKhacTraNo = new Date();

  @ViewChildren(MatPaginator) paginator = new QueryList<MatPaginator>();

  ngOnInit() {
    this.dataSourceChuyenTien.paginator = this.paginator.toArray()[0];
    this.dataSourceNhanTien.paginator = this.paginator.toArray()[1];
    this.dataSourceTraNo.paginator = this.paginator.toArray()[2];
    this.dataSourceNguoiKhacTraNo.paginator = this.paginator.toArray()[3];
    this.getLichSuGiaoDich();
  }

  constructor(
    private webStorageSerivce: WebStorageSerivce,
    private lichsugiaodichService: LichsugiaodichService,
    private matDialog: MatDialog,
    public datepipe: DatePipe
  ) { }

  getLichSuGiaoDich() {
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    const value = {
      manguoigui: decodeAT.mataikhoan
    }
    this.lichsugiaodichService.getLichSuGiaoDich(value).subscribe((res: any) => {
      console.log(res);
      this.dataSourceChuyenTien.data = res.danhSachChuyenTien;
      this.dataSourceNhanTien.data = res.danhSachNhanTien;
      this.dataSourceTraNo.data = res.danhSachTraNo;
      this.dataSourceNguoiKhacTraNo.data = res.danhSachNguoiKhacTraNo;
      this.soLuongChuyenTien = res.danhSachChuyenTien.length;
      this.soLuongNhanTien = res.danhSachNhanTien.length;
      this.soLuongMinhTraNo = res.danhSachTraNo.length;
      this.soLuongNguoiKhacTraNo = res.danhSachNguoiKhacTraNo.length;
    });
  }

  getChuyenTien() {
    if(this.startChuyenTien > this.endChuyenTien) {
      this.showErorrDialog('Ngày bắt đầu phải trước ngày kết thúc');
      return;
    }

    const startCT = this.datepipe.transform(this.startChuyenTien, 'dd/MM/yyyy');
    const endCT = this.datepipe.transform(this.endChuyenTien, 'dd/MM/yyyy');
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    const value = {
      manguoigui: decodeAT.mataikhoan,
      ngaybatdau: startCT,
      ngayketthuc: endCT
    };
    this.lichsugiaodichService.getChuyenTien(value).subscribe((res: any) => {
      console.log(res);
      this.dataSourceChuyenTien.data = res.danhSachChuyenTien;
      this.soLuongChuyenTien = res.danhSachChuyenTien.length;
    })
  }

  getNhanTien() {
    if(this.startNhanTien > this.endNhanTien) {
      this.showErorrDialog('Ngày bắt đầu phải trước ngày kết thúc');
      return;
    }

    const startNT = this.datepipe.transform(this.startNhanTien, 'dd/MM/yyyy');
    const endNT = this.datepipe.transform(this.endNhanTien, 'dd/MM/yyyy');
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    const value = {
      manguoigui: decodeAT.mataikhoan,
      ngaybatdau: startNT,
      ngayketthuc: endNT
    };
    this.lichsugiaodichService.getNhanTien(value).subscribe((res: any) => {
      console.log(res);
      this.dataSourceNhanTien.data = res.danhSachNhanTien;
      this.soLuongNhanTien = res.danhSachNhanTien.length;
    })
  }

  getTraNo() {
    if(this.startTraNo > this.endTraNo) {
      this.showErorrDialog('Ngày bắt đầu phải trước ngày kết thúc');
      return;
    }

    const startTN = this.datepipe.transform(this.startTraNo, 'dd/MM/yyyy');
    const endTN = this.datepipe.transform(this.endTraNo, 'dd/MM/yyyy');
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    const value = {
      manguoigui: decodeAT.mataikhoan,
      ngaybatdau: startTN,
      ngayketthuc: endTN
    };
    this.lichsugiaodichService.getTraNo(value).subscribe((res: any) => {
      console.log(res);
      this.dataSourceTraNo.data = res.danhSachTraNo;
      this.soLuongMinhTraNo = res.danhSachTraNo.length;
    })
  }

  getNguoiKhacTraNo() {
    if(this.startNguoiKhacTraNo > this.endNguoiKhacTraNo) {
      this.showErorrDialog('Ngày bắt đầu phải trước ngày kết thúc');
      return;
    }

    const startNKTN = this.datepipe.transform(this.startNguoiKhacTraNo, 'dd/MM/yyyy');
    const endNKTN = this.datepipe.transform(this.endNguoiKhacTraNo, 'dd/MM/yyyy');
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    const value = {
      manguoigui: decodeAT.mataikhoan,
      ngaybatdau: startNKTN,
      ngayketthuc: endNKTN
    };
    this.lichsugiaodichService.getNguoiKhacTraNo(value).subscribe((res: any) => {
      console.log(res);
      this.dataSourceNguoiKhacTraNo.data = res.danhSachNguoiKhacTraNo;
      this.soLuongNguoiKhacTraNo = res.danhSachNguoiKhacTraNo.length;
    })
  }

  showErorrDialog(text: any){
    this.matDialog.open(DialogErrorsComponent, {
      disableClose: true,
      height: '300px',
      width: '300px',
      data: { message: text }
    });
  }

}
