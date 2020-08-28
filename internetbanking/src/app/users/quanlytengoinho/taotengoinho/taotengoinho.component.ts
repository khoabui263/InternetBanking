import { Component, OnInit } from '@angular/core';
import { GoinhoService } from 'src/app/services/goinho.service';
import { WebStorageSerivce } from 'src/app/services/webstorage.service';
import * as jwt_decode from 'jwt-decode';
import { DialogErrorsComponent } from 'src/app/share/dialog-errors/dialog-errors.component';
import { MatDialog } from '@angular/material';
import { DialogNoticeComponent } from 'src/app/share/dialog-notice/dialog-notice.component';
import { DialogActivegoinhoComponent } from '../../dialogs/dialog-activegoinho/dialog-activegoinho.component';
import { NganhangService } from 'src/app/services/nganhang.service';

@Component({
  selector: 'app-taotengoinho',
  templateUrl: './taotengoinho.component.html',
  styleUrls: ['./taotengoinho.component.scss']
})
export class TaotengoinhoComponent implements OnInit {
  founded = false;
  public tenGoiNhoModel: any = {
    account: '',
    fullName: '',
    nickName: ''
  };

  selectedBank = 1;
  banks: any[] = [];

  constructor(
    private goinhoService: GoinhoService,
    private webStorageSerivce: WebStorageSerivce,
    private nganhangService: NganhangService,
    private matDialog: MatDialog
  ) { }

  ngOnInit() {
    this.getDanhSachNganHang();
  }

  getDanhSachNganHang() {
    this.nganhangService.getDanhSachNganHang().subscribe((res: any) => {
      console.log(res);
      this.banks = res.danhSachNganHang;
    });
  }

  findGoiNhoExisted() {
    if (!this.tenGoiNhoModel.account) {
      this.showErorrDialog('Vui lòng điền tài khoàn gợi nhớ')
    }

    if (this.selectedBank === 1) {
      const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
      const value = {
        mataikhoancannho: decodeAT.mataikhoan,
        mataikhoangoinho: this.tenGoiNhoModel.account,
        manganhang: this.selectedBank
      }
      this.goinhoService.findGoiNhoExisted(value).subscribe((res: any) => {
        console.log(res);
        if (res.active === '0') {
          this.tenGoiNhoModel.fullName = res.message;
          this.founded = true;

        } else if (res.active === '1') {
          this.showErorrDialog('Tài khoản không tồn tại. Vui lòng kiểm tra lại thông tin');

        } else if (res.active === '2') {
          const success = this.matDialog.open(DialogActivegoinhoComponent, {
            disableClose: true,
            height: '300px',
            width: '300px',
            data: {
              message: 'Bạn đã từng lưu tên gợi nhớ này. Bạn có muốn kích hoạt lại không ?',
              mataikhoangoinho: this.tenGoiNhoModel.account,
              manganhang: this.selectedBank
            }
          });
          success.afterClosed().subscribe(() => {
            this.reset();
          });

        } else if (res.active === '3') {
          this.showErorrDialog('Bạn đã lưu tài khoản này');

        } else if (res.active === '4') {
          this.showErorrDialog('Đây là tài khoản của chính bạn');

        }
      })
    } // end of if
  }

  saveGoiNho() {
    if (!this.tenGoiNhoModel.nickName) {
      this.showErorrDialog('Vui lòng điền biệt danh');
    }

    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    const value = {
      mataikhoancannho: decodeAT.mataikhoan,
      mataikhoangoinho: this.tenGoiNhoModel.account,
      chuoimanguoigoinho: this.tenGoiNhoModel.account+'',
      hotennguoigoinho: this.tenGoiNhoModel.fullName,
      bietdanhgoinho: this.tenGoiNhoModel.nickName,
      manganhang: this.selectedBank,
      trangthai: 1
    };

    console.log(value);
    this.goinhoService.saveReminder(value).subscribe((res: any) => {
      console.log(res);
      if(res.active === '1') {
        this.showErorrDialog('Lưu thông tin gợi nhớ thất bại');

      } else if(res.active === '0') {
        this.showErorrDialog('Lưu thông tin gợi nhớ thành công');

      }
      this.reset();
    })

  }

  showErorrDialog(text: any) {
    this.matDialog.open(DialogErrorsComponent, {
      disableClose: true,
      height: '300px',
      width: '300px',
      data: { message: text }
    });
  }

  reset() {
    this.tenGoiNhoModel.account = '';
    this.tenGoiNhoModel.fullName = '';
    this.tenGoiNhoModel.nickName = '';
    this.founded = false;
  }

}
