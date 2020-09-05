import { Component, OnInit, Inject } from '@angular/core';
import { TaiKhoanDangNhapService } from 'src/app/services/taikhoandangnhap.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-dialog-capnhatnhanvien',
  templateUrl: './dialog-capnhatnhanvien.component.html',
  styleUrls: ['./dialog-capnhatnhanvien.component.scss']
})
export class DialogCapnhatnhanvienComponent implements OnInit {
  saved = true;
  ketQua: string;

  mataikhoan: number;
  hoten: string;
  email: string;
  sodienthoai: string;

  constructor(
    private taiKhoanDangNhapService: TaiKhoanDangNhapService,
    public dialogRef: MatDialogRef<DialogCapnhatnhanvienComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit() {
    this.mataikhoan = this.data.mataikhoan;
    this.hoten = this.data.hoten;
    this.email = this.data.email;
    this.sodienthoai = this.data.sodienthoai;
  }

  updateEmployee() {
    const value = {
      mataikhoan: this.mataikhoan,
      hoten: this.hoten,
      email: this.email,
      sodienthoai: this.sodienthoai
    }
    console.log(value);
    this.taiKhoanDangNhapService.updateEmployee(value).subscribe((res: any) => {
      if(res.active === '1') {
        this.ketQua = 'Cập nhật thông tin thất bại';
      } else {
        this.ketQua = 'Cập nhật thông tin thành công';
      }
      this.saved = !this.saved;
    })
  }

  closeModal() {
    this.dialogRef.close();
  }

}
