import { Component, OnInit } from '@angular/core';
import { WebStorageSerivce } from 'src/app/services/webstorage.service';
import { TaiKhoanDangNhapService } from 'src/app/services/taikhoandangnhap.service';
import { MatDialog } from '@angular/material';
import { DialogErrorsComponent } from 'src/app/share/dialog-errors/dialog-errors.component';

@Component({
  selector: 'app-taotaikhoan',
  templateUrl: './taotaikhoan.component.html',
  styleUrls: ['./taotaikhoan.component.scss']
})
export class TaotaikhoanComponent implements OnInit {
  confirm = false;
  showSpinner = false;

  taoTaiKhoanModel: any = {
    hoten: '',
    email: '',
    soDienThoai: '',
    money: 0,
    password: '',
    otp: ''
  }

  constructor(
    private webStorageSerivce: WebStorageSerivce,
    private taiKhoanDangNhapService: TaiKhoanDangNhapService,
    private matDialog: MatDialog,
  ) { }

  ngOnInit() {
  }

  sendEmailConfirmInfo() {
    this.showSpinner = !this.showSpinner;
    if (!this.taoTaiKhoanModel.hoten) {
      this.showErorrDialog('Vui lòng điền thông tin họ tên');
      return;
    }

    if (!this.taoTaiKhoanModel.email) {
      this.showErorrDialog('Vui lòng điền thông tin email');
      return;
    }

    if (!this.taoTaiKhoanModel.soDienThoai) {
      this.showErorrDialog('Vui lòng điền thông tin số điện thoại');
      return;
    }

    if (this.taoTaiKhoanModel.money < 100000) {
      this.showErorrDialog('Sồ tiền nạp lần đầu tối thiểu là 100.000 VNĐ');
      return;
    }
    const value = {
      email: this.taoTaiKhoanModel.email,
      sodienthoai: this.taoTaiKhoanModel.soDienThoai,
    };
    this.taiKhoanDangNhapService.sendEmailConfirmInfo(value).subscribe((res: any) => {
      this.showSpinner = !this.showSpinner;
      console.log(res);
      if (res.active === '1') {
        this.showErorrDialog('Email và số điện thoại này đã được sử dụng');
      } else if (res.active === '2') {
        this.showErorrDialog('Gửi mail thất bại');
      } else {
        this.showErorrDialog('Vui lòng kiểm tra Email để nhập mã xác nhận OTP');
        this.confirm = true;
      }
    });
  }

  confirmInfo() {
    if (!this.taoTaiKhoanModel.password) {
      this.showErorrDialog('Vui lòng điền mật khẩu');
      return;
    }

    if (!this.taoTaiKhoanModel.otp) {
      this.showErorrDialog('Vui lòng check mail và điền mã OTP');
      return;
    }
    const value = {
      hoten: this.taoTaiKhoanModel.hoten,
      email: this.taoTaiKhoanModel.email,
      sodienthoai: this.taoTaiKhoanModel.soDienThoai,
      sotien: this.taoTaiKhoanModel.money,
      matkhau: this.taoTaiKhoanModel.password,
      otp: this.taoTaiKhoanModel.otp
    };
    console.log(value);
    this.taiKhoanDangNhapService.confirmInfo(value).subscribe((res: any) => {
      if (res.active === '1') {
        const fail = this.matDialog.open(DialogErrorsComponent, {
          disableClose: true,
          height: '300px',
          width: '300px',
          data: { message: 'Tạo tài khoản thất bại' }
        });
        fail.afterClosed().subscribe(() => {
          this.reset();
        });

      } else {
        const success = this.matDialog.open(DialogErrorsComponent, {
          disableClose: true,
          height: '300px',
          width: '300px',
          data: { message: 'Tạo tài khoản thành công' }
        });
        success.afterClosed().subscribe(() => {
          this.reset();
        });
      }
    });
    this.reset();
  }

  reset() {
    this.confirm = false;
    this.taoTaiKhoanModel.hoten = '';
    this.taoTaiKhoanModel.email = '';
    this.taoTaiKhoanModel.soDienThoai = '';
    this.taoTaiKhoanModel.money = 0;
    this.taoTaiKhoanModel.password = '';
    this.taoTaiKhoanModel.otp = '';
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
