import { Component, OnInit, Inject } from '@angular/core';
import { GoinhoService } from 'src/app/services/goinho.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-dialog-updategoinho',
  templateUrl: './dialog-updategoinho.component.html',
  styleUrls: ['./dialog-updategoinho.component.scss']
})
export class DialogUpdategoinhoComponent implements OnInit {
  message: string;
  bietdanhgoinho: string;
  saved = true;
  ketQua: string;

  constructor(
    private goinhoService: GoinhoService,
    public dialogRef: MatDialogRef<DialogUpdategoinhoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit() {
    this.message = this.data.bietdanhgoinho;
  }

  saveReminder() {
    const value = {
      id: this.data.id,
      mataikhoancannho: this.data.mataikhoancannho,
      mataikhoangoinho: this.data.mataikhoangoinho,
      chuoimanguoigoinho: this.data.chuoimanguoigoinho,
      hotennguoigoinho: this.data.hotennguoigoinho,
      bietdanhgoinho: this.bietdanhgoinho,
      manganhang: this.data.manganhang,
      trangthai: 1
    }

    this.goinhoService.saveReminder(value).subscribe((res: any) => {
      if(res.active === '1') {
        this.ketQua = 'Cập nhật thông tin gợi nhớ thất bại';

      } else if(res.active === '0') {
        this.ketQua = 'Cập nhật thông tin gợi nhớ thành công';

      }
      this.saved = false;
    })
  }

  closeModal() {
    this.dialogRef.close();
  }

}
