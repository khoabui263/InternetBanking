import { Component, OnInit } from '@angular/core';
import { WebStorageSerivce } from '../services/webstorage.service';
import { Router } from '@angular/router';
import * as jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.scss']
})
export class EmployeesComponent implements OnInit {
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
