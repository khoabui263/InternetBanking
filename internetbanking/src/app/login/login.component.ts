import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { MatDialog } from '@angular/material';
import { DialogErrorsComponent } from '../share/dialog-errors/dialog-errors.component';
import { WebStorageSerivce } from '../services/webstorage.service';
import { WebKeyStorage } from '../globlas/web-key-storage';
import { AuthenticateService } from '../services/authenticate.service';
import * as jwt_decode from 'jwt-decode';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public loginModel: any = {
    taiKhoan: '',
    matKhau: '',
    validate: null,
  }

  resolved(captchaResponse: string) {
    this.loginModel.validate = `${captchaResponse}`;
  }

  constructor(
    private router: Router,
    private authenticateService: AuthenticateService,
    private webStorageSerivce: WebStorageSerivce,
    public matDialog: MatDialog,
    ) { }

  ngOnInit() {
  }

  onLogin(){
    if(!this.loginModel.taiKhoan){
      this.showErorrDialog('Vui lòng điền thông tin tài khoản');
      return;
    }

    if(!this.loginModel.matKhau){
      this.showErorrDialog('Vui lòng điền thông tin mật khẩu');
      return;
    }

    if(!(this.loginModel.validate)){ 
      this.showErorrDialog('Vui lòng điền captcha');
      return;
    }

    const value = {
      userName: this.loginModel.taiKhoan,
      passWord: this.loginModel.matKhau
    }
    this.authenticateService.login(value).subscribe((res: any) =>{
      if(res.active === '1'){
        this.showErorrDialog('Sai thông tin đăng nhập. Vui lòng đăng nhập lại');

      } else {
        console.log(res);
        this.webStorageSerivce.setLocalStorage(
        WebKeyStorage.token_info, { accessToken: res.accessToken, refreshToken: res.refreshToken, userName: res.userName });

        const decodeAT = jwt_decode(res.accessToken);
        if(decodeAT.role === 1){
          this.router.navigateByUrl('/admin');

        } else if(decodeAT.role === 2){
          this.router.navigateByUrl('/employees');

        } else if(decodeAT.role === 3){
          this.router.navigateByUrl('/users');

        }
      }



    })
    console.log(this.loginModel.taiKhoan);
    console.log(this.loginModel.matKhau);
  }

  onRefeshToken(){
    this.authenticateService.test().subscribe((result: any) => {
      console.log(result);
    });
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
