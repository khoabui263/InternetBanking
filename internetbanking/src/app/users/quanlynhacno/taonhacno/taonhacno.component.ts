import { Component, OnInit } from '@angular/core';
import { TaiKhoanDangNhapService } from 'src/app/services/taikhoandangnhap.service';
import { WebStorageSerivce } from 'src/app/services/webstorage.service';
import * as jwt_decode from 'jwt-decode';
import { MatDialog } from '@angular/material';
import { DialogErrorsComponent } from 'src/app/share/dialog-errors/dialog-errors.component';
import { NhacnoService } from 'src/app/services/nhacno.service';

@Component({
  selector: 'app-taonhacno',
  templateUrl: './taonhacno.component.html',
  styleUrls: ['./taonhacno.component.scss']
})
export class TaonhacnoComponent implements OnInit {
  public nhacNoModel: any = {
    debtReminded: '',
    accountReceiveDebt: 0,
    money: 0,
    content: ''
  };

  danhSachTaiKhoanNhanNo: any[] = [];

  constructor(
    private taiKhoanDangNhapService: TaiKhoanDangNhapService,
    private webStorageSerivce: WebStorageSerivce,
    private nhacnoService: NhacnoService,
    private matDialog: MatDialog,
  ) { }

  ngOnInit() {
    this.getAccount();
  }

  getAccount() {
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    const value = {
      mataikhoan: decodeAT.mataikhoan
    }
    this.taiKhoanDangNhapService.findAllDetailsTaiKhoanDangNhap(value).subscribe((res: any) => {
      console.log(res);
      if (res.active === '1') {
        this.showErorrDialog('Tài khoản thanh toán hiện chưa có. Vui lòng liên hệ với nhân viên');

      } else {
        this.danhSachTaiKhoanNhanNo = res.DanhSachTaiKhoan;
      }

    })
  }

  saveDebt() {
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    if(!this.nhacNoModel.debtReminded) {
      this.showErorrDialog('Vui lòng điền thông tin cần nhắc nợ');
      return;

    }

    if(this.nhacNoModel.debtReminded === decodeAT.sub) {
      this.showErorrDialog('Bạn không thể tự nhắc nợ mình');
      return;

    }

    if(!this.nhacNoModel.accountReceiveDebt) {
      this.showErorrDialog('Vui lòng chọn tài khoản nhận tiền trả nợ');
      return;

    }

    if (this.nhacNoModel.money < 10000) {
      this.showErorrDialog('Số tiền nhắc nợ tối thiểu là 10.000 VND');
      return;

    }
    const value = {
      mataikhoannhacno: this.nhacNoModel.accountReceiveDebt,
      manguoinhacno: decodeAT.mataikhoan,
      emailsodienthoai: this.nhacNoModel.debtReminded,
      sotienno: this.nhacNoModel.money,
      noidungnhacno: this.nhacNoModel.content
    }
    console.log(value);

    this.nhacnoService.saveNhacNo(value).subscribe((res: any) => {
      console.log(res);
      if(res.active === 2){
        this.showErorrDialog('Không tìm thấy tài khoản này trong hệ thống. Vui lòng kiểm tra lại thông tin tài khoản');
      } else if(res.active === 1){
        this.showErorrDialog('Lưu thông tin nhắc nợ thất bại');
      } else {
        this.reset();
        this.showErorrDialog('Lưu thông tin nhắc nợ thành công');
      }
    });
  }

  reset() {
    this.nhacNoModel.debtReminded = '';
    this.nhacNoModel.accountReceiveDebt = 0;
    this.nhacNoModel.money = 0;
    this.nhacNoModel.content = '';
  }

  showErorrDialog(text: any) {
    this.matDialog.open(DialogErrorsComponent, {
      disableClose: true,
      height: '300px',
      width: '300px',
      data: { message: text }
    });
  }
}
