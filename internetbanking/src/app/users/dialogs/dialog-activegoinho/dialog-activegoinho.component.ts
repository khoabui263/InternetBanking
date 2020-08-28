import { Component, OnInit, Inject } from '@angular/core';
import { GoinhoService } from 'src/app/services/goinho.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { WebStorageSerivce } from 'src/app/services/webstorage.service';
import * as jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-dialog-activegoinho',
  templateUrl: './dialog-activegoinho.component.html',
  styleUrls: ['./dialog-activegoinho.component.scss']
})
export class DialogActivegoinhoComponent implements OnInit {
  message: string;
  ketQua: string;
  saved = true;
  mataikhoangoinho: any;
  manganhang: any;

  constructor(
    private goinhoService: GoinhoService,
    private webStorageSerivce: WebStorageSerivce,
    public dialogRef: MatDialogRef<DialogActivegoinhoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit() {
    this.message = this.data.message;
    this.mataikhoangoinho = this.data.mataikhoangoinho;
    this.manganhang = this.data.manganhang;
  }

  activate() {
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    const value = {
      mataikhoancannho: decodeAT.mataikhoan,
      mataikhoangoinho: this.mataikhoangoinho,
      manganhang: this.manganhang
    }
    this.goinhoService.activateGoiNho(value).subscribe((res: any) => {
      if(res.active === '0'){
        this.ketQua = 'Kích hoạt tên gợi nhớ thành công';
      } else {
        this.ketQua = 'Kích hoạt tên gợi nhớ thất bại';
      }
    })
    this.saved = false;
  }

  closeModal() {
    this.dialogRef.close();
  }

}
