import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-taotaikhoan',
  templateUrl: './taotaikhoan.component.html',
  styleUrls: ['./taotaikhoan.component.scss']
})
export class TaotaikhoanComponent implements OnInit {
  taoTaiKhoanModel: any = {
    hoten: '',
    email: '',
    soDienThoai: '',
    money: 0,
    otp: ''
  }

  constructor() { }

  ngOnInit() {
  }

}
