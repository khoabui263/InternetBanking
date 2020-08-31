import { Component, OnInit, Inject } from '@angular/core';
import { GoinhoService } from 'src/app/services/goinho.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { TaiKhoanDangNhapService } from 'src/app/services/taikhoandangnhap.service';
import * as jwt_decode from 'jwt-decode';
import { WebStorageSerivce } from 'src/app/services/webstorage.service';
import { element } from 'protractor';
import { NhacnoService } from 'src/app/services/nhacno.service';

@Component({
  selector: 'app-dialog-thanhtoanno',
  templateUrl: './dialog-thanhtoanno.component.html',
  styleUrls: ['./dialog-thanhtoanno.component.scss']
})
export class DialogThanhtoannoComponent implements OnInit {
  selectedAccount = 0;
  danhSachTaiKhoanThanhToan: any[] = [];
  saved = true;
  Error = true;
  Result = true;
  loi: string;
  ketQua: string;

  constructor(
    private webStorageSerivce: WebStorageSerivce,
    private taiKhoanDangNhapService: TaiKhoanDangNhapService,
    private nhacnoService: NhacnoService,
    public dialogRef: MatDialogRef<DialogThanhtoannoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit() {
    this.getDanhSachTaiKhoanThanhToan();

  }

  getDanhSachTaiKhoanThanhToan() {
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    const value = {
      mataikhoan: decodeAT.mataikhoan
    }
    this.taiKhoanDangNhapService.findAllDetailsTaiKhoanDangNhap(value).subscribe((res: any) => {
      console.log(res);
      this.danhSachTaiKhoanThanhToan = res.DanhSachTaiKhoan;
    });
  }

  saveDebt() {
    console.log(this.selectedAccount);
    if (this.selectedAccount === 0) {
      this.loi = 'Vui lòng chọn tài khoản để trả nợ';
      this.Error = false;
      return;
    }

    this.danhSachTaiKhoanThanhToan.forEach(element => {
      if ((element.mataikhoanthanhtoan === this.selectedAccount) &&
        (Number(element.sodu)) < (Number(this.data.sotienno))) {
        this.loi = 'Tài khoản này không đủ tiền để trả nợ';
        this.Error = false;
        return;
      }
    });

    const value = {
      id: this.data.id,
      manguoinhacno: this.data.manguoinhacno,
      manguoibino: this.data.manguoibino,
      mataikhoannhacno: this.data.mataikhoannhacno,
      mataikhoanduocnhacno: this.selectedAccount,
      hotennguoinhacno: this.data.hotennguoinhacno,
      hotennguoibino: this.data.hotennguoibino,
      sotienno: this.data.sotienno,
      noidungnhacno: this.data.noidungnhacno
    };

    console.log(value);
    this.nhacnoService.payDebt(value).subscribe((res: any) => {
      console.log(res);
      if (res.active === '1') {
        this.loi = 'Thanh toán nợ thất bại';
        this.Error = false;
      } else {
        this.ketQua = 'Thanh toán nợ thành công';
        this.Error = true;
        this.Result = false;
      }
      this.dialogRef.close(res);
    })

  }

  closeModal() {
    this.dialogRef.close({
      active: 0
    });
  }

}
