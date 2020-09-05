import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { DatePipe } from '@angular/common';
import { LichsugiaodichService } from 'src/app/services/lichsugiaodich.service';
import * as jwt_decode from 'jwt-decode';
import { DialogErrorsComponent } from 'src/app/share/dialog-errors/dialog-errors.component';
import { MatDialog } from '@angular/material';
import { DialogEmployeelichsugiaodichComponent } from '../../dialogs/dialog-employeelichsugiaodich/dialog-employeelichsugiaodich.component';

@Component({
  selector: 'app-lichsugiaodich',
  templateUrl: './lichsugiaodich.component.html',
  styleUrls: ['./lichsugiaodich.component.scss']
})
export class LichsugiaodichComponent implements OnInit {
  displayedColumns: string[] = [
    'mataikhoannguoigui', 'mataikhoannguoinhan', 'sotiengiaodich', 'noidungchuyenkhoan', 'ngaygiaodich', 'chitiet'];
  dataSource = new MatTableDataSource();
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  account = '';
  start = new Date(new Date().setDate(new Date().getDate() - 30));
  end = new Date();

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
  }

  constructor(
    private lichsugiaodichService: LichsugiaodichService,
    private matDialog: MatDialog,
    public datepipe: DatePipe
  ) { }

  getLichSuGiaoDichByEmployee() {
    if (!this.account) {
      this.showErorrDialog('Vui lòng điền email hoặc số điện thoại');
      return;
    }
    if (this.start > this.end) {
      this.showErorrDialog('Ngày bắt đầu phải trước ngày kết thúc');
      return;
    }

    const startTN = this.datepipe.transform(this.start, 'dd/MM/yyyy');
    const endTN = this.datepipe.transform(this.end, 'dd/MM/yyyy');
    const value = {
      email: this.account,
      ngaybatdau: startTN,
      ngayketthuc: endTN
    };
    this.lichsugiaodichService.getLichSuGiaoDichByEmployee(value).subscribe((res: any) => {
      console.log(res);
      if(res.active === '1') {
        this.showErorrDialog('Không tìm thấy tài khoản. Vui lòng kiểm tra lại email hoặc số điện thoại');
        this.dataSource.data = [];
        return;
      } else if(res.length === 0) {
        this.showErorrDialog('Tài khoản này chưa có bất kì giao dịch nào');
        this.dataSource.data = [];
        return;
      } else {
        this.dataSource.data = res;
      }
    });
  }

  getLichSuGiaoDichByEmployeeDetails(ma: number) {
    this.matDialog.open(DialogEmployeelichsugiaodichComponent, {
      disableClose: true,
      height: '600px',
      width: '500px',
      data: { id: ma }
    });
  }

  showErorrDialog(text: any) {
    this.matDialog.open(DialogErrorsComponent, {
      disableClose: true,
      height: '300px',
      width: '300px',
      data: { message: text }
    });
  }

}
