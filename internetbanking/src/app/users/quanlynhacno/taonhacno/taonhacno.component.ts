import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-taonhacno',
  templateUrl: './taonhacno.component.html',
  styleUrls: ['./taonhacno.component.scss']
})
export class TaonhacnoComponent implements OnInit {
  public nhacNoModel: any = {
    debtReminder: '',
    debtReminded: '',
    money: 0,

  }

  constructor() { }

  ngOnInit() {
  }

}
