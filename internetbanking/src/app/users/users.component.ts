import { Component, OnInit } from '@angular/core';
import { WebStorageSerivce } from '../services/webstorage.service';
import { Router } from '@angular/router';
import * as jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
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
