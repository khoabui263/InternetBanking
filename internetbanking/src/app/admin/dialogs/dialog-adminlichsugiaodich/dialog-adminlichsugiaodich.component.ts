import { Inject } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { LichsugiaodichService } from 'src/app/services/lichsugiaodich.service';
import { WebStorageSerivce } from 'src/app/services/webstorage.service';

@Component({
  selector: 'app-dialog-adminlichsugiaodich',
  templateUrl: './dialog-adminlichsugiaodich.component.html',
  styleUrls: ['./dialog-adminlichsugiaodich.component.scss']
})
export class DialogAdminlichsugiaodichComponent implements OnInit {
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
  signaturebengui: any;
  signaturebennhan: any;

  constructor(
    private webStorageSerivce: WebStorageSerivce,
    private lichsugiaodichService: LichsugiaodichService,
    public dialogRef: MatDialogRef<DialogAdminlichsugiaodichComponent>,
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
      this.signaturebengui = res.signaturebengui;
      this.signaturebennhan = res.signaturebennhan;
    });
  }

  closeModal() {
    this.dialogRef.close();
  }

}
