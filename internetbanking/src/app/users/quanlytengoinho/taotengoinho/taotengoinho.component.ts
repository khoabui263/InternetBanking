import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-taotengoinho',
  templateUrl: './taotengoinho.component.html',
  styleUrls: ['./taotengoinho.component.scss']
})
export class TaotengoinhoComponent implements OnInit {
  public tenGoiNhoModel: any = {
    account: '',
    fullName: '',
    nickName: ''
  }

  constructor() { }

  ngOnInit() {
  }

  remember(){
  }

}
