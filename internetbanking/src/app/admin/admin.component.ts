import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WebStorageSerivce } from '../services/webstorage.service';
import * as jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  name: string;
  constructor(
    private router: Router,
    private webStorageSerivce: WebStorageSerivce,
  ) { }

  ngOnInit() {
    this.getName();
  }

  logOut(){
    this.webStorageSerivce.clearLocalStorage();
    this.router.navigateByUrl('/');
  }

  getName(){
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    this.name = decodeAT.name;
  }

}
