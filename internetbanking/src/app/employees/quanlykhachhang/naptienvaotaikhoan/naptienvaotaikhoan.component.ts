import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';

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
    selectedType: 'account'
  }

  showSender = true;


  types: any[] = [
    {value: 'account', viewValue: 'Chuyển khoản qua tài khoản khác'},
    {value: 'cash', viewValue: 'Nạp tiền mặt vào tài khoản'},
  ];

  constructor() { }

  ngOnInit() {
  }

  changeTypeTranfers(value: any){
    console.log(typeof(value));
    this.napTienModel.selectedType = value.value;
    if(this.napTienModel.selectedType === 'cash'){
      this.showSender = false;
    } else {
      this.showSender = true;
    }
  }

}
