import { Component, OnInit, OnDestroy } from '@angular/core';
import { WebStorageSerivce } from '../services/webstorage.service';
import { Router } from '@angular/router';
import * as jwt_decode from 'jwt-decode';
import { MatDialog } from '@angular/material';
import { DialogErrorsComponent } from '../share/dialog-errors/dialog-errors.component';
import { DialogThongbaonoComponent } from './dialogs/dialog-thongbaono/dialog-thongbaono.component';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit, OnDestroy {
  EventSource: any;
  name: string;
  constructor(
    private router: Router,
    private matDialog: MatDialog,
    private webStorageSerivce: WebStorageSerivce,
  ) { }

  ngOnDestroy(): void {
    if (this.EventSource) {
      this.EventSource.close();
    }
  }

  ngOnInit() {
    this.getName();
    this.nhacNoRealTime();
  }

  logOut() {
    this.webStorageSerivce.clearLocalStorage();
    this.router.navigateByUrl('/');
    this.EventSource.close();
  }

  getName() {
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    this.name = decodeAT.name;
  }

  nhacNoRealTime(): void {
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    this.EventSource = new EventSource('http://localhost:8080/api/realtime/checkno/' + decodeAT.mataikhoan);
    this.EventSource.addEventListener('message', message => {
      console.log(message);
      const tinhTrangNo = JSON.parse(message.data);
      if (tinhTrangNo === 1) {
        const chuaThanhToan = this.matDialog.open(DialogThongbaonoComponent, {
          disableClose: true,
          height: '300px',
          width: '300px',
          data: { message: 'Bạn có 1 món nợ chưa thanh toán. Bạn có muốn chuyển trang tới đó không ?' }
        });
        chuaThanhToan.afterClosed().subscribe(data => {
          if(data === 'change') {
            this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
              this.router.navigate(['/users/home/list-debt']);
            });
          }
        })
      } else if (tinhTrangNo === 3) {
        const chuaThanhToan = this.matDialog.open(DialogThongbaonoComponent, {
          disableClose: true,
          height: '300px',
          width: '300px',
          data: { message: 'Có người từ chối thanh toán nợ cho bạn. Bạn có muốn chuyển trang tới đó không ?' }
        });
        chuaThanhToan.afterClosed().subscribe(data => {
          if(data === 'change') {
            this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
              this.router.navigate(['/users/home/list-debt']);
            });
          }
        })
      }
    });
  }

}
