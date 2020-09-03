import { Component, OnInit, ViewChild, QueryList, ViewChildren, AfterViewInit, OnDestroy } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatDialog } from '@angular/material';
import { WebStorageSerivce } from 'src/app/services/webstorage.service';
import { NhacnoService } from 'src/app/services/nhacno.service';
import * as jwt_decode from 'jwt-decode';
import { DialogUpdatenhacnoComponent } from '../../dialogs/dialog-updatenhacno/dialog-updatenhacno.component';
import { DialogThanhtoannoComponent } from '../../dialogs/dialog-thanhtoanno/dialog-thanhtoanno.component';

@Component({
  selector: 'app-danhsachnhacno',
  templateUrl: './danhsachnhacno.component.html',
  styleUrls: ['./danhsachnhacno.component.scss']
})
export class DanhsachnhacnoComponent implements OnInit, AfterViewInit, OnDestroy {
  EventSource: any;
  SoluongTaoNo: number;
  SoluongChuaThanhToan: number;
  SoluongTuChoiThanhToan: number;

  displayedColumnsTaoNo: string[] = ['hotennguoibinoTaoNo', 'sotiennoTaoNo', 'noidungnhacnoTaoNo', 'chinhsuaTaoNo'];
  displayedColumnsChuaThanhToan: string[] = ['hotennguoinhacnoChuaThanhToan', 'sotiennoChuaThanhToan',
    'noidungnhacnoChuaThanhToan', 'chinhsuaChuaThanhToan'];
  displayedColumnsTuChoiThanhToan: string[] = ['hotennguoinhacnoTuChoiThanhToan', 'sotiennoTuChoiThanhToan',
    'noidungnhacnoTuChoiThanhToan', 'chinhsuaTuChoiThanhToan'];

  dataSourceTaoNo = new MatTableDataSource();
  dataSourceChuaThanhToan = new MatTableDataSource();
  dataSourceTuChoiThanhToan = new MatTableDataSource();
  @ViewChildren(MatPaginator) paginator = new QueryList<MatPaginator>();

  ngOnInit() {
    this.getDanhSachNo();
    this.nhacNoRealTime();
  }

  ngAfterViewInit() {
    this.dataSourceTaoNo.paginator = this.paginator.toArray()[0];
    this.dataSourceChuaThanhToan.paginator = this.paginator.toArray()[1];
    this.dataSourceTuChoiThanhToan.paginator = this.paginator.toArray()[2];
  }

  constructor(
    private webStorageSerivce: WebStorageSerivce,
    private nhacNoService: NhacnoService,
    private matDialog: MatDialog
  ) { }

  ngOnDestroy(): void {
    if(this.EventSource){
      this.EventSource.close();
    }
  }

  getDanhSachNo() {
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    const value = {
      manguoinhacno: decodeAT.mataikhoan
    };

    this.nhacNoService.getDanhSachNo(value).subscribe((res: any) => {
      console.log(res);
      this.dataSourceTaoNo.data = res.danhSachTaoNo;
      this.dataSourceChuaThanhToan.data = res.danhSachChuaThanhToanNo;
      this.dataSourceTuChoiThanhToan.data = res.danhSachTuChoiThanhToan;
      this.SoluongTaoNo = res.danhSachTaoNo.length;
      this.SoluongChuaThanhToan = res.danhSachChuaThanhToanNo.length;
      this.SoluongTuChoiThanhToan = res.danhSachTuChoiThanhToan.length;
    });
  }

  nhacNoRealTime() {
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    this.EventSource = new EventSource('http://localhost:8080/api/realtime/getDanhSachNo/' + decodeAT.mataikhoan);
    this.EventSource.addEventListener('message', message => {
      const danhSachNo = JSON.parse(message.data);
      console.log(danhSachNo);
      this.dataSourceTaoNo.data = danhSachNo.danhSachTaoNo;
      this.dataSourceChuaThanhToan.data = danhSachNo.danhSachChuaThanhToanNo;
      this.dataSourceTuChoiThanhToan.data = danhSachNo.danhSachTuChoiThanhToan;
      this.SoluongTaoNo = danhSachNo.danhSachTaoNo.length;
      this.SoluongChuaThanhToan = danhSachNo.danhSachChuaThanhToanNo.length;
      this.SoluongTuChoiThanhToan = danhSachNo.danhSachTuChoiThanhToan.length;
    });
  }

  update(ma: number, nhacno, bino, status: number) {
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    if (status === 4) {
      const success = this.matDialog.open(DialogUpdatenhacnoComponent, {
        disableClose: true,
        height: '300px',
        width: '500px',
        data: {
          message: 'Bạn có muốn hủy nhắc nợ này không ?',
          id: ma,
          manguoinhacno: nhacno,
          manguoibino: bino,
          maloainhacno: status
        }
      });

      success.afterClosed().subscribe((res) => {
        console.log(res);
        if (res.active === 0) {
          console.log('closed');
        } else {
          this.dataSourceTaoNo.data = res.danhSachTaoNo;
          this.dataSourceChuaThanhToan.data = res.danhSachChuaThanhToanNo;
          this.dataSourceTuChoiThanhToan.data = res.danhSachTuChoiThanhToan;
          this.SoluongTaoNo = res.danhSachTaoNo.length;
          this.SoluongChuaThanhToan = res.danhSachChuaThanhToanNo.length;
          this.SoluongTuChoiThanhToan = res.danhSachTuChoiThanhToan.length;
        }
      });

    } else if (status === 3) {
      const success = this.matDialog.open(DialogUpdatenhacnoComponent, {
        disableClose: true,
        height: '300px',
        width: '500px',
        data: {
          message: 'Bạn chắc chắn không muốn trả phần nhắc nợ này ?',
          id: ma,
          manguoinhacno: nhacno,
          manguoibino: bino,
          maloainhacno: status
        }
      });

      success.afterClosed().subscribe((res) => {
        console.log(res);
        if (res.active === 0) {
          console.log('closed');
        } else {
          this.dataSourceTaoNo.data = res.danhSachTaoNo;
          this.dataSourceChuaThanhToan.data = res.danhSachChuaThanhToanNo;
          this.dataSourceTuChoiThanhToan.data = res.danhSachTuChoiThanhToan;
          this.SoluongTaoNo = res.danhSachTaoNo.length;
          this.SoluongChuaThanhToan = res.danhSachChuaThanhToanNo.length;
          this.SoluongTuChoiThanhToan = res.danhSachTuChoiThanhToan.length;
        }
      });

    } else if (status === 1) {
      const success = this.matDialog.open(DialogUpdatenhacnoComponent, {
        disableClose: true,
        height: '300px',
        width: '500px',
        data: {
          message: 'Bạn có muốn tiếp tục nhắc nợ không ?',
          id: ma,
          manguoinhacno: nhacno,
          manguoibino: bino,
          maloainhacno: status
        }
      });

      success.afterClosed().subscribe((res) => {
        console.log(res);
        if (res.active === 0) {
          console.log('closed');
        } else {
          this.dataSourceTaoNo.data = res.danhSachTaoNo;
          this.dataSourceChuaThanhToan.data = res.danhSachChuaThanhToanNo;
          this.dataSourceTuChoiThanhToan.data = res.danhSachTuChoiThanhToan;
          this.SoluongTaoNo = res.danhSachTaoNo.length;
          this.SoluongChuaThanhToan = res.danhSachChuaThanhToanNo.length;
          this.SoluongTuChoiThanhToan = res.danhSachTuChoiThanhToan.length;
        }
      });
    }
  }

  payDebt(ma: number, nguoiNhacNo, nguoiBiNo, taiKhoanNhacNo, hoTenNhacNo, hoTenBiNo, soTien, noiDung) {
    const success = this.matDialog.open(DialogThanhtoannoComponent, {
      disableClose: true,
      height: '300px',
      width: '500px',
      data: {
        id: ma,
        manguoinhacno: nguoiNhacNo,
        manguoibino: nguoiBiNo,
        mataikhoannhacno: taiKhoanNhacNo,
        hotennguoinhacno: hoTenNhacNo,
        hotennguoibino: hoTenBiNo,
        sotienno: soTien,
        noidungnhacno: noiDung
      }
    });

    success.afterClosed().subscribe((res) => {
      console.log(res);
      if (res.active === 0) {
        console.log('closed');
      } else {
        this.dataSourceTaoNo.data = res.danhSachTaoNo;
        this.dataSourceChuaThanhToan.data = res.danhSachChuaThanhToanNo;
        this.dataSourceTuChoiThanhToan.data = res.danhSachTuChoiThanhToan;
        this.SoluongTaoNo = res.danhSachTaoNo.length;
        this.SoluongChuaThanhToan = res.danhSachChuaThanhToanNo.length;
        this.SoluongTuChoiThanhToan = res.danhSachTuChoiThanhToan.length;
      }
    });
  }

}
