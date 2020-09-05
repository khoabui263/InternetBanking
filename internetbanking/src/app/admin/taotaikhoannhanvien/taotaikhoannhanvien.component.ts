import { Component, OnInit } from '@angular/core';
import { TaiKhoanDangNhapService } from 'src/app/services/taikhoandangnhap.service';
import { DialogErrorsComponent } from 'src/app/share/dialog-errors/dialog-errors.component';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-taotaikhoannhanvien',
  templateUrl: './taotaikhoannhanvien.component.html',
  styleUrls: ['./taotaikhoannhanvien.component.scss']
})
export class TaotaikhoannhanvienComponent implements OnInit {
  public taoTaiKhoanModel = {
    hoten: '',
    email: '',
    soDienThoai: '',
    matKhau: '',
    xacNhanMatKhau: ''
  };

  constructor(
    private taiKhoanDangNhapService: TaiKhoanDangNhapService,
    public matDialog: MatDialog
  ) { }

  ngOnInit() {
  }

  createEmployee() {
    if (!this.taoTaiKhoanModel.hoten) {
      this.showErorrDialog('Vui lòng điền họ tên');
      return;
    }

    if (!this.taoTaiKhoanModel.email) {
      this.showErorrDialog('Vui lòng điền email');
      return;
    }

    if (!this.taoTaiKhoanModel.soDienThoai) {
      this.showErorrDialog('Vui lòng điền số điện thoại');
      return;
    }

    if (!this.taoTaiKhoanModel.matKhau) {
      this.showErorrDialog('Vui lòng điền mật khẩu');
      return;
    }

    if (!this.taoTaiKhoanModel.xacNhanMatKhau) {
      this.showErorrDialog('Vui lòng điền xác nhận mật khẩu');
      return;
    }

    if (this.taoTaiKhoanModel.matKhau !== this.taoTaiKhoanModel.xacNhanMatKhau) {
      this.showErorrDialog('Nội dung xác nhận mật khẩu không trùng với mật khẩu mới');
      return;
    }

    const value = {
      hoten: this.taoTaiKhoanModel.hoten,
      email: this.taoTaiKhoanModel.email,
      sodienthoai: this.taoTaiKhoanModel.soDienThoai,
      matkhau: this.taoTaiKhoanModel.matKhau
    };
    console.log(value);
    this.taiKhoanDangNhapService.createEmployee(value).subscribe((res: any) => {
      console.log(res);
      if (res.active === '2') {
        this.showErorrDialog('Email và số điện thoại này đã được sử dụng');
        this.reset();
      } else if (res.active === '1') {
        this.showErorrDialog('Tạo tài khoản nhân viên thất bại');
        this.reset();
      } else {
        this.showErorrDialog('Tạo tài khoản nhân viên thành công');
        this.reset();
      }
    })
  }

  reset() {
    this.taoTaiKhoanModel.hoten = '';
    this.taoTaiKhoanModel.email = '';
    this.taoTaiKhoanModel.soDienThoai = '';
    this.taoTaiKhoanModel.matKhau = '';
    this.taoTaiKhoanModel.xacNhanMatKhau = '';
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
