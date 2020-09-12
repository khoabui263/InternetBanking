import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { AuthenticateService } from '../services/authenticate.service';
import { DialogErrorsComponent } from '../share/dialog-errors/dialog-errors.component';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.scss']
})
export class ForgetPasswordComponent implements OnInit {
  existed = false;
  showSpinner = false;
  account = '';
  newPassWord = '';
  confirmPassWord = '';
  otp = '';

  constructor(
    private authenticateService: AuthenticateService,
    public matDialog: MatDialog
  ) { }

  ngOnInit() {
  }

  sendEmailCheckAccountExisted() {
    if (!this.account) {
      this.showErorrDialog('Vui lòng điền thông tin email');
      return;
    }
    this.showSpinner = true;
    const value = {
      email: this.account
    }
    this.authenticateService.sendEmailCheckAccountExisted(value).subscribe((res: any) => {
      this.showSpinner = false;
      if (res.active === '1') {
        this.showErorrDialog('Không tìm thấy tài khoản. Vui lòng kiểm tra lại thông tin');
        return;

      } else {
        this.showErorrDialog('Vui lòng kiểm tra email để nhập mã xác nhận OTP');
        this.existed = true;
      }
    });
  }

  forgetPassword() {
    if (!this.otp) {
      this.showErorrDialog('Vui lòng điền mã OTP');
      return;
    }
    if (!this.newPassWord) {
      this.showErorrDialog('Vui lòng điền thông tin mật khẩu');
      return;
    }
    if (!this.confirmPassWord) {
      this.showErorrDialog('Vui lòng điền thông tin xác nhận mật khẩu');
      return;
    }
    if (this.newPassWord !== this.confirmPassWord) {
      this.showErorrDialog('Nội dung xác nhận mật khẩu không trùng với mật khẩu mới');
      return;
    }
    const value = {
      email: this.account,
      otp: this.otp,
      newpassword: this.newPassWord
    }
    this.authenticateService.forgetPassword(value).subscribe((res: any) => {
      console.log(res);
      if (res.active === '1') {
        this.showErorrDialog('Cập nhật mật khẩu thất bại');
        this.reset();

      } else {
        this.showErorrDialog('Cập nhật mật khẩu thành công');
        this.reset();
      }
    });
  }

  reset() {
    this.existed = false;
    this.showSpinner = false;
    this.account = '';
    this.newPassWord = '';
    this.confirmPassWord = '';
    this.otp = '';
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
