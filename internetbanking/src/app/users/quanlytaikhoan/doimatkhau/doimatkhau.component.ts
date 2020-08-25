import { Component, OnInit } from '@angular/core';
import { TaiKhoanDangNhapService } from 'src/app/services/taikhoandangnhap.service';
import { WebStorageSerivce } from 'src/app/services/webstorage.service';
import { SearchService } from 'src/app/services/search.service';
import { MatDialog } from '@angular/material';
import { DialogErrorsComponent } from 'src/app/share/dialog-errors/dialog-errors.component';
import * as jwt_decode from 'jwt-decode';
import { DialogNoticeComponent } from 'src/app/share/dialog-notice/dialog-notice.component';

@Component({
  selector: 'app-doimatkhau',
  templateUrl: './doimatkhau.component.html',
  styleUrls: ['./doimatkhau.component.scss']
})
export class DoimatkhauComponent implements OnInit {
  confirmChangePwd = false;
  public changePassModel: any = {
    oldPassWord: '',
    newPassWord: '',
    confirmPassWord: '',
    otp: ''
  }

  constructor(
    private taiKhoanDangNhapService: TaiKhoanDangNhapService,
    private webStorageSerivce: WebStorageSerivce,
    private searchService: SearchService,
    private matDialog: MatDialog,
  ) { }

  ngOnInit() {
  }

  sendEmailConfirm() {
    if (!this.changePassModel.oldPassWord) {
      this.showErorrDialog('Vui lòng điền mật khẩu cũ');
      return;

    } else if (!this.changePassModel.newPassWord) {
      this.showErorrDialog('Vui lòng điền mật khẩu mới');
      return;

    } else if (!this.changePassModel.confirmPassWord) {
      this.showErorrDialog('Vui lòng điền xác nhận mật khẩu');
      return;

    } else if (this.changePassModel.newPassWord !== this.changePassModel.confirmPassWord) {
      this.showErorrDialog('Nội dung xác nhận mật khẩu không trùng với mật khẩu mới');
      return;

    }
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    const value = {
      email: decodeAT.sub,
      matkhau: this.changePassModel.oldPassWord
    }

    this.taiKhoanDangNhapService.sendEmailChangePassWord(value).subscribe((res: any) => {
      console.log(res);
      if (res.active === '1') {
        this.showErorrDialog('Sai mật khẩu. Vui lòng nhập lại');
      }
      if (res.active === '0') {
        this.confirmChangePwd = true;
        this.showErorrDialog('Vui lòng kiểm tra Email để nhập mã xác nhận OTP');
      }
    })
  }

  confirmChangePassWord() {
    if (!this.changePassModel.otp) {
      this.showErorrDialog('Vui lòng điền mã OTP');
      return;

    }

    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    const value = {
      email: decodeAT.sub,
      otp: this.changePassModel.otp,
      oldpassword: this.changePassModel.oldPassWord,
      newpassword: this.changePassModel.newPassWord
    }

    this.taiKhoanDangNhapService.confirmChangePassWord(value).subscribe((res: any) => {
      if (res.active === '1') {
        this.showErorrDialog('Cập nhật mật khẩu thất bại');

      } else if (res.active === '0') {
        // this.showErorrDialog('Cập nhật mật khẩu thành công');
        // this.confirmChangePwd = false;
        const success = this.matDialog.open(DialogErrorsComponent, {
          disableClose: true,
          height: '300px',
          width: '300px',
          data: { message: 'Cập nhật mật khẩu thành công' }
        });

        success.afterClosed().subscribe(() => {
          this.changePassModel.oldPassWord = '';
          this.changePassModel.newPassWord = '';
          this.changePassModel.confirmPassWord = '';
          this.changePassModel.otp = '';
          this.confirmChangePwd = false;

        })
      }
    })

  }

  reset() {
    this.changePassModel.oldPassWord = '';
    this.changePassModel.newPassWord = '';
    this.changePassModel.confirmPassWord = '';
    this.changePassModel.otp = '';
    this.confirmChangePwd = false;
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
