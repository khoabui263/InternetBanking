import { Component, OnInit, Inject } from '@angular/core';
import { NhacnoService } from 'src/app/services/nhacno.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-dialog-updatenhacno',
  templateUrl: './dialog-updatenhacno.component.html',
  styleUrls: ['./dialog-updatenhacno.component.scss']
})
export class DialogUpdatenhacnoComponent implements OnInit {
  message: string;

  constructor(
    private nhacNoService: NhacnoService,
    public dialogRef: MatDialogRef<DialogUpdatenhacnoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
    ) { }

  ngOnInit() {
    this.message = this.data.message;
  }

  updateLoaiNhacNo() {
    const value = {
      id: this.data.id,
      manguoinhacno: this.data.manguoinhacno,
      manguoibino: this.data.manguoibino,
      maloainhacno: this.data.maloainhacno
    }
    this.nhacNoService.updateLoaiNhacNo(value).subscribe((res: any) => {
      this.dialogRef.close(res);
    })
  }

  closeModal() {
    this.dialogRef.close({
      active: 0
    });
  }

}
