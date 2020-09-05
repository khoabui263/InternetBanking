import { Component, OnInit, Inject } from '@angular/core';
import { TaiKhoanDangNhapService } from 'src/app/services/taikhoandangnhap.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-dialog-xoanhanvien',
  templateUrl: './dialog-xoanhanvien.component.html',
  styleUrls: ['./dialog-xoanhanvien.component.scss']
})
export class DialogXoanhanvienComponent implements OnInit {
  ketQua: string;
  saved = true;
  message = 'Bạn có muốn xóa nhân viên này không ?';
  constructor(
    private taiKhoanDangNhapService: TaiKhoanDangNhapService,
    public dialogRef: MatDialogRef<DialogXoanhanvienComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit() {
  }

  deleteEmployee() {
    const value = {
      mataikhoan: this.data.mataikhoan
    }
    this.taiKhoanDangNhapService.deleteEmployee(value).subscribe((res: any) => {
      if (res.active === '1') {
        this.ketQua = 'Xóa nhân viên thất bại';
      } else {
        this.ketQua = 'Xóa nhân viên thành công';
      }
      this.saved = !this.saved;
    })
  }

  closeModal() {
    this.dialogRef.close();
  }

}
