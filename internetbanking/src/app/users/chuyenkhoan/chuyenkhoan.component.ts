import { Component, OnInit, ViewChild } from '@angular/core';
import { MatStepper } from '@angular/material/stepper';
import { TaikhoanthanhtoanService } from 'src/app/services/taikhoanthanhtoan.service';
import { WebStorageSerivce } from 'src/app/services/webstorage.service';
import { Router } from '@angular/router';
import { DialogErrorsComponent } from 'src/app/share/dialog-errors/dialog-errors.component';
import { MatDialog } from '@angular/material';
import * as jwt_decode from 'jwt-decode';
import { TaiKhoanDangNhapService } from 'src/app/services/taikhoandangnhap.service';
import { DialogReminderComponent } from '../dialogs/dialog-reminder/dialog-reminder.component';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap, flatMap } from 'rxjs/operators';
import { SearchService } from 'src/app/services/search.service';
import { NganhangService } from 'src/app/services/nganhang.service';
import { LiennganhangService } from 'src/app/services/liennganhang.service';

@Component({
  selector: 'app-chuyenkhoan',
  templateUrl: './chuyenkhoan.component.html',
  styleUrls: ['./chuyenkhoan.component.scss']
})
export class ChuyenkhoanComponent implements OnInit {
  @ViewChild('stepper', { static: true }) myStepper: MatStepper;
  isLinear = true;
  showSpinner = false;
  public chuyenKhoanModel: any = {
    accountReceiver: '',
    hoTenNguoiNhan: '',
    hoTenNguoiGui: '',
    accountTransfer: 0,
    money: 0,
    content: '',
    selectedPayer: '0',
    OTP: '',
  };

  private searchTerms = new Subject<string>();
  list$: Observable<any>;
  Show = true;

  danhSachTaiKhoanChuyen: any[] = [];

  payers: any[] = [
    { id: '0', name: 'Người nhận trả phí' },
    { id: '1', name: 'Người gửi trả phí' }
  ];

  selectedBank = 1;
  banks: any[] = [];

  constructor(
    private taikhoanthanhtoanService: TaikhoanthanhtoanService,
    private taiKhoanDangNhapService: TaiKhoanDangNhapService,
    private lienganhangService: LiennganhangService,
    private webStorageSerivce: WebStorageSerivce,
    private searchService: SearchService,
    private nganhangService: NganhangService,
    private router: Router,
    private matDialog: MatDialog

  ) { }

  search(term: string): void {
    this.searchTerms.next(term);
    this.Show = true;
    this.chuyenKhoanModel.accountReceiver = term;
    console.log('gán: ' + this.chuyenKhoanModel.accountReceiver);

  }

  ngOnInit() {
    this.getDanhSachNganHang();
    this.list$ = this.searchTerms.pipe(
      debounceTime(500),
      distinctUntilChanged(),
      switchMap((term: string) => this.searchService.searchGoiNho(term, this.selectedBank))
    );
  }

  fill(name: string) {
    this.chuyenKhoanModel.accountReceiver = name;
    this.Show = false;
    this.searchTerms.next('');
  }

  getDanhSachNganHang() {
    this.nganhangService.getDanhSachNganHang().subscribe((res: any) => {
      console.log(res);
      this.banks = res.danhSachNganHang;
    });
  }

  finish1() {
    if (!this.chuyenKhoanModel.accountReceiver) {
      this.showErorrDialog('Vui lòng điền tài khoản nhận');
      return;
    }

    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    if (this.selectedBank === 1) {
      const value = {
        mataikhoandangnhap: decodeAT.mataikhoan,
        mataikhoanthanhtoan: this.chuyenKhoanModel.accountReceiver
      };
      this.taikhoanthanhtoanService.findTaiKhoanGuiVaNhan(value).subscribe((res: any) => {
        console.log(res);
        if (res.active === '1') {
          this.showErorrDialog('Không tìm thấy tài khoản cần chuyển. Vui lòng kiểm tra lại thông tin');

        } else if (res.active === '2') {
          this.showErorrDialog('Bạn không thể tự chuyển khoản cho các tài khoản của mình');

        } else {
          this.chuyenKhoanModel.hoTenNguoiNhan = res.hotentaikhoannhan;
          this.chuyenKhoanModel.hoTenNguoiGui = res.danhsachtaikhoangui[0].hoten;
          this.danhSachTaiKhoanChuyen = res.danhsachtaikhoangui;
          this.myStepper.next();

        }
      });

    } else if (this.selectedBank === 2) {
      const value = {
        email: decodeAT.sub,
        mataikhoandangnhap: decodeAT.mataikhoan,
        mataikhoanthanhtoan: Number(this.chuyenKhoanModel.accountReceiver)
      };
      this.lienganhangService.localFindAccountRSA(value).subscribe((res: any) => {
        console.log(res);
        if (res.active === '1') {
          this.showErorrDialog('Không tìm thấy tài khoản cần chuyển. Vui lòng kiểm tra lại thông tin');

        } else if (res.active === '2') {
          this.showErorrDialog('Bạn không thể tự chuyển khoản cho các tài khoản của mình');

        } else {
          this.chuyenKhoanModel.hoTenNguoiNhan = res.hotentaikhoannhan;
          this.chuyenKhoanModel.hoTenNguoiGui = res.danhsachtaikhoangui[0].hoten;
          this.danhSachTaiKhoanChuyen = res.danhsachtaikhoangui;
          this.myStepper.next();

        }
      });

    } else if (this.selectedBank === 3) {

    }
  }

  finish2() {
    this.showSpinner = !this.showSpinner;
    let limit = true;
    if (this.chuyenKhoanModel.accountTransfer === 0) {
      this.showErorrDialog('Vui lòng chọn tài khoản chuyển tiền');
      return;
    }

    if (this.chuyenKhoanModel.money < 10000) {
      this.showErorrDialog('Số tiền gửi tối thiểu là 10.000 VND');
      return;

    } else {
      this.danhSachTaiKhoanChuyen.forEach(element => {
        if ((element.mataikhoanthanhtoan === this.chuyenKhoanModel.accountTransfer) &&
          (Number(element.sodu)) <= (this.chuyenKhoanModel.money + 5000)) {
          limit = false;
        }
      });

      if (!limit) {
        this.showErorrDialog('Số tiền gửi vượt quá giới hạn số dư trong tài khoản');
        return;
      }
    }

    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    const value = {
      email: decodeAT.sub
    };
    this.taiKhoanDangNhapService.sendEmailChuyenTien(value).subscribe((res: any) => {
      this.showSpinner = !this.showSpinner;
      if (res.active === '1') {
        this.showErorrDialog('Xác nhận thông tin thất bại');

      } else if (res.active === '0') {
        this.myStepper.next();
        this.showErorrDialog('Vui lòng kiểm tra Email để nhập mã xác nhận OTP');
      }
    });

  }

  finish3() {
    if (!this.chuyenKhoanModel.OTP) {
      this.showErorrDialog('Vui lòng điền mã OTP');
      return;
    }

    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    if (this.selectedBank === 1) {
      const value = {
        mataikhoancannho: decodeAT.mataikhoan,
        email: decodeAT.sub,
        mataikhoannguoigui: this.chuyenKhoanModel.accountTransfer,
        tennguoigui: this.chuyenKhoanModel.hoTenNguoiGui,
        mataikhoannguoinhan: Number(this.chuyenKhoanModel.accountReceiver),
        tennguoinhan: this.chuyenKhoanModel.hoTenNguoiNhan,
        sotiengiaodich: this.chuyenKhoanModel.money,
        noidungchuyenkhoan: this.chuyenKhoanModel.content,
        nguoitraphi: Number(this.chuyenKhoanModel.selectedPayer),
        manganhanggui: 1,
        manganhangnhan: 1,
        otp: Number(this.chuyenKhoanModel.OTP)
      };

      console.log(value);
      this.taikhoanthanhtoanService.confirmTransfer(value).subscribe((res: any) => {
        console.log(res);
        if (res.active === '1') {
          const fail = this.matDialog.open(DialogErrorsComponent, {
            disableClose: true,
            height: '300px',
            width: '300px',
            data: { message: 'Chuyển khoản thất bại vì nhập mã OTP sai hoặc hết hiệu lực' }
          });
          fail.afterClosed().subscribe(() => {
            this.reset();
          });

        } else if (res.active === '0') {
          const success = this.matDialog.open(DialogErrorsComponent, {
            disableClose: true,
            height: '300px',
            width: '300px',
            data: { message: 'Chuyển khoản thành công' }
          });
          success.afterClosed().subscribe(() => {
            this.reset();
          });

        } else {
          const success = this.matDialog.open(DialogReminderComponent, {
            disableClose: true,
            height: '300px',
            width: '550px',
            data: {
              mataikhoancannho: res.mataikhoancannho,
              mataikhoangoinho: res.mataikhoangoinho,
              chuoimanguoigoinho: res.chuoimanguoigoinho,
              hotennguoigoinho: res.hotennguoigoinho,
              manganhang: res.manganhang,
              trangthai: res.trangthai
            }
          });
          success.afterClosed().subscribe(() => {
            this.reset();
          });
        }
      });

    } else if (this.selectedBank === 2) {
      const value = {
        mataikhoancannho: decodeAT.mataikhoan,
        email: decodeAT.sub,
        mataikhoannguoigui: this.chuyenKhoanModel.accountTransfer,
        tennguoigui: this.chuyenKhoanModel.hoTenNguoiGui,
        mataikhoannguoinhan: Number(this.chuyenKhoanModel.accountReceiver),
        tennguoinhan: this.chuyenKhoanModel.hoTenNguoiNhan,
        sotiengiaodich: this.chuyenKhoanModel.money,
        noidungchuyenkhoan: this.chuyenKhoanModel.content,
        nguoitraphi: Number(this.chuyenKhoanModel.selectedPayer),
        manganhanggui: 1,
        manganhangnhan: 2,
        otp: Number(this.chuyenKhoanModel.OTP)
      };
      this.lienganhangService.confirmTransferLocalToRSA(value).subscribe((res: any) => {
        console.log(res);
        if (res.active === '1') {
          const fail = this.matDialog.open(DialogErrorsComponent, {
            disableClose: true,
            height: '300px',
            width: '300px',
            data: { message: 'Chuyển khoản thất bại vì nhập mã OTP sai hoặc hết hiệu lực' }
          });
          fail.afterClosed().subscribe(() => {
            this.reset();
          });

        } else if (res.active === '0') {
          const success = this.matDialog.open(DialogErrorsComponent, {
            disableClose: true,
            height: '300px',
            width: '300px',
            data: { message: 'Chuyển khoản thành công' }
          });
          success.afterClosed().subscribe(() => {
            this.reset();
          });

        } else {
          const success = this.matDialog.open(DialogReminderComponent, {
            disableClose: true,
            height: '300px',
            width: '550px',
            data: {
              mataikhoancannho: res.mataikhoancannho,
              mataikhoangoinho: res.mataikhoangoinho,
              chuoimanguoigoinho: res.chuoimanguoigoinho,
              hotennguoigoinho: res.hotennguoigoinho,
              manganhang: res.manganhang,
              trangthai: res.trangthai
            }
          });
          success.afterClosed().subscribe(() => {
            this.reset();
          });
        }
      });

    } else if (this.selectedBank === 3) {

    }


  }

  reset() {
    this.chuyenKhoanModel.fullName = '';
    this.chuyenKhoanModel.accountReceiver = '';
    this.chuyenKhoanModel.hoTenNguoiNhan = '';
    this.chuyenKhoanModel.hoTenNguoiGui = '';
    this.chuyenKhoanModel.accountTransfer = 0;
    this.chuyenKhoanModel.money = 0;
    this.chuyenKhoanModel.content = '';
    this.chuyenKhoanModel.selectedPayer = '0';
    this.chuyenKhoanModel.OTP = '';
    this.danhSachTaiKhoanChuyen = [];
    this.selectedBank = 1;
    this.myStepper.reset();
  }

  loadData() {
    this.showSpinner = !this.showSpinner;
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
