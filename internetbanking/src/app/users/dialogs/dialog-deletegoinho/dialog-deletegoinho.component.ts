import { Component, OnInit, Inject } from '@angular/core';
import { GoinhoService } from 'src/app/services/goinho.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-dialog-deletegoinho',
  templateUrl: './dialog-deletegoinho.component.html',
  styleUrls: ['./dialog-deletegoinho.component.scss']
})
export class DialogDeletegoinhoComponent implements OnInit {
  saved = true;
  ketQua: string;

  constructor(
    private goinhoService: GoinhoService,
    public dialogRef: MatDialogRef<DialogDeletegoinhoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit() {
  }

  deleteReminder() {
    const value = {
      id: this.data.id
    }
    this.goinhoService.deleteReminder(value).subscribe((res: any) => {
      if(res.active === '1') {
        this.ketQua = 'Xóa thông tin gợi nhớ thất bại';

      } else if(res.active === '0') {
        this.ketQua = 'Xóa thông tin gợi nhớ thành công';

      }
      this.saved = false;
    })
  }

  closeModal() {
    this.dialogRef.close();
  }

}
