import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-doimatkhau',
  templateUrl: './doimatkhau.component.html',
  styleUrls: ['./doimatkhau.component.scss']
})
export class DoimatkhauComponent implements OnInit {
  public changePassModel: any = {
    oldPassWord: '',
    newPassWord: '',
    confirmPassWord: ''
  }

  constructor() { }

  ngOnInit() {
  }

}
