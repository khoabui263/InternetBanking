import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-dialog-thongbaono',
  templateUrl: './dialog-thongbaono.component.html',
  styleUrls: ['./dialog-thongbaono.component.scss']
})
export class DialogThongbaonoComponent implements OnInit {
  message = '';

  constructor(
    public dialogRef: MatDialogRef<DialogThongbaonoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit() {
    this.message = this.data.message;
  }

  chuyenTrang() {
    this.dialogRef.close('change');
  }

  closeModal() {
    this.dialogRef.close();
  }

}
