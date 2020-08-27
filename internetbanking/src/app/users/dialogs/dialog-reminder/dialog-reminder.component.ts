import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { GoinhoService } from 'src/app/services/goinho.service';

@Component({
  selector: 'app-dialog-reminder',
  templateUrl: './dialog-reminder.component.html',
  styleUrls: ['./dialog-reminder.component.scss']
})
export class DialogReminderComponent implements OnInit {
  message = 'Bạn có muốn ghi nhớ tài khoản này không ?';
  saved = true;
  ketQua: string;

  mataikhoancannho: any;
  mataikhoangoinho: any;
  chuoimanguoigoinho: any;
  hotennguoigoinho: any;
  bietdanhgoinho: string;
  manganhang: any;

  constructor(
    private goinhoService: GoinhoService,
    public dialogRef: MatDialogRef<DialogReminderComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit() {
    this.mataikhoancannho = this.data.mataikhoancannho;
    this.mataikhoangoinho = this.data.mataikhoangoinho;
    this.chuoimanguoigoinho = this.data.chuoimanguoigoinho;
    this.hotennguoigoinho = this.data.hotennguoigoinho;
    this.manganhang = this.data.manganhang;
  }

  saveReminder() {
    const value = {
      mataikhoancannho: this.mataikhoancannho,
      mataikhoangoinho: this.mataikhoangoinho,
      chuoimanguoigoinho: this.chuoimanguoigoinho,
      hotennguoigoinho: this.hotennguoigoinho,
      bietdanhgoinho: this.bietdanhgoinho,
      manganhang: this.manganhang
    }

    this.goinhoService.saveReminder(value).subscribe((res: any) => {
      console.log(res);
      if(res.active === '1') {
        this.ketQua = 'Lưu thông tin gợi nhớ thất bại';

      } else if(res.active === '0') {
        this.ketQua = 'Lưu thông tin gợi nhớ thành công';

      }
      this.saved = false;
    })
  }

  closeModal() {
    this.dialogRef.close();
  }

}
