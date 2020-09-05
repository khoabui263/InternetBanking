import { Component, OnInit, Inject } from '@angular/core';
import { WebStorageSerivce } from 'src/app/services/webstorage.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { LichsugiaodichService } from 'src/app/services/lichsugiaodich.service';

@Component({
  selector: 'app-dialog-employeelichsugiaodich',
  templateUrl: './dialog-employeelichsugiaodich.component.html',
  styleUrls: ['./dialog-employeelichsugiaodich.component.scss']
})
export class DialogEmployeelichsugiaodichComponent implements OnInit {
  mataikhoannguoigui: any;
  mataikhoannguoinhan: any;
  ngaygiaodich: any;
  noidungchuyenkhoan: any;
  signature: any;
  sotiengiaodich: any;
  tenloaigiaodich: any;
  tennganhanggui: any;
  tennganhangnhan: any;
  tennguoigui: any;
  tennguoinhan: any;
  constructor(
    private webStorageSerivce: WebStorageSerivce,
    private lichsugiaodichService: LichsugiaodichService,
    public dialogRef: MatDialogRef<DialogEmployeelichsugiaodichComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit() {
    this.getLichSuGiaoDichByEmployeeDetails();
  }

  getLichSuGiaoDichByEmployeeDetails() {
    this.lichsugiaodichService.getLichSuGiaoDichByEmployeeDetails({ id: this.data.id }).subscribe((res: any) => {
      console.log(res);
      this.tennguoigui = res.tennguoigui;
      this.tennguoinhan = res.tennguoinhan;
      this.mataikhoannguoigui = res.mataikhoannguoigui;
      this.mataikhoannguoinhan = res.mataikhoannguoinhan;
      this.sotiengiaodich = res.sotiengiaodich;
      this.noidungchuyenkhoan = res.noidungchuyenkhoan;
      this.ngaygiaodich = res.ngaygiaodich;
      this.tennganhanggui = res.tennganhanggui;
      this.tennganhangnhan = res.tennganhangnhan;
      this.tenloaigiaodich = res.tenloaigiaodich;
    });
  }

  closeModal() {
    this.dialogRef.close();
  }
}
