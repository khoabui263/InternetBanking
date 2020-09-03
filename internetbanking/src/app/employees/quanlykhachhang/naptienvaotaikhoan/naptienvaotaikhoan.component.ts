import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { WebStorageSerivce } from 'src/app/services/webstorage.service';
import { MatDialog } from '@angular/material';
import { DialogErrorsComponent } from 'src/app/share/dialog-errors/dialog-errors.component';
import { TaikhoanthanhtoanService } from 'src/app/services/taikhoanthanhtoan.service';

@Component({
  selector: 'app-naptienvaotaikhoan',
  templateUrl: './naptienvaotaikhoan.component.html',
  styleUrls: ['./naptienvaotaikhoan.component.scss']
})
export class NaptienvaotaikhoanComponent implements OnInit {
  napTienModel: any = {
    accountSender: '',
    accountReceive: '',
    money: 0,
    selectedType: 0
  }

  showSender = true;

  types: any[] = [
    {value: 0, viewValue: 'Chuyển khoản qua tài khoản khác'},
    {value: 1, viewValue: 'Nạp tiền mặt vào tài khoản'},
  ];

  constructor(
    private webStorageSerivce: WebStorageSerivce,
    private matDialog: MatDialog,
    private taikhoanthanhtoanService: TaikhoanthanhtoanService,
  ) { }

  ngOnInit() {
  }

  changeTypeTranfers(value: any){
    console.log(typeof(value));
    this.napTienModel.selectedType = value.value;
    if(this.napTienModel.selectedType === 1){
      this.showSender = false;
    } else {
      this.showSender = true;
    }
  }

  chargeMoney() {
    if(this.napTienModel.selectedType === 0 && !this.napTienModel.accountSender) {
      this.showErorrDialog('Vui lòng điền mã tài khoản người gửi');
      return;
    }
    if(!this.napTienModel.accountReceive) {
      this.showErorrDialog('Vui lòng điền mã tài khoản người nhận');
      return;
    }
    if (this.napTienModel.money < 10000) {
      this.showErorrDialog('Số tiền nạp tối thiểu là 10.000 VND');
      return;
    }
    const value = {
      mataikhoanchuyen: this.napTienModel.accountSender,
      mataikhoannhan: this.napTienModel.accountReceive,
      money: this.napTienModel.money,
      type: this.napTienModel.selectedType
    };

    this.taikhoanthanhtoanService.chargeMoney(value).subscribe((res: any) => {
      console.log(res);
      if(res.active === '1') {
        this.showErorrDialog('Tài khoản gửi hoặc nhận không tồn tại. Vui lòng kiểm tra lại thông tin');
        return;
      } else if(res.active === '2') {
        this.showErorrDialog('Số tiền gửi lớn hơn số dư trong tài khoản gửi');
        return;
      } else if(res.active === '3') {
        this.showErorrDialog('Nạp tiền thất bại');
        return;
      } else if(res.active === '0') {
        this.showErorrDialog('Nạp tiền thành công');
        this.reset();
      }
    });
  }

  reset() {
    this.napTienModel.accountSender = '';
    this.napTienModel.accountReceive = '';
    this.napTienModel.money = 0;
    this.napTienModel.selectedType = 0;
    this.showSender = true;
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
