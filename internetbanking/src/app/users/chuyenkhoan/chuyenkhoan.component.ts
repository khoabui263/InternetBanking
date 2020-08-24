import { Component, OnInit, ViewChild } from '@angular/core';
import { MatStepper } from '@angular/material/stepper';

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
    fullName: 'Khoa',
    accountTransfer: '',
    money: '',
    content: '',
    selectedPayer: '0',
    OTP: '',
  }

  payers: any[] = [
    { id: '0', name: 'Người nhận trả phí'},
    { id: '1', name: 'Người gửi trả phí'}
  ];

  selectedBank = 'local';
  banks: any[] = [
    {value: 'local', viewValue: 'Chuyển khoản nội bộ'},
    {value: 'rsa', viewValue: 'Ngân hàng RSA'},
    {value: 'pgp', viewValue: 'Ngân hàng PGP'}
  ];


  constructor() { }

  ngOnInit() {

  }

  finish1(){
    this.myStepper.next();
    console.log(this.chuyenKhoanModel.fullName);

  }

  finish2(){
    this.myStepper.next();
  }

  reset(){
    this.chuyenKhoanModel.fullName = '';
    this.myStepper.reset();
  }

  loadData(){
    this.showSpinner = !this.showSpinner;
  }

}
