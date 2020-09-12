import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { DatePipe } from '@angular/common';
import { LichsugiaodichService } from 'src/app/services/lichsugiaodich.service';
import { MatDialog } from '@angular/material';
import { NganhangService } from 'src/app/services/nganhang.service';
import { DialogErrorsComponent } from 'src/app/share/dialog-errors/dialog-errors.component';
import { DialogAdminlichsugiaodichComponent } from '../dialogs/dialog-adminlichsugiaodich/dialog-adminlichsugiaodich.component';

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
  selectedBank = 1;
  banks: any[] = [];

  ngOnInit() {
    this.getDanhSachNganHang();
    this.dataSource.paginator = this.paginator;
  }

  constructor(
    private lichsugiaodichService: LichsugiaodichService,
    private nganhangService: NganhangService,
    private matDialog: MatDialog,
    public datepipe: DatePipe
  ) { }

  getDanhSachNganHang() {
    this.nganhangService.getDanhSachNganHang().subscribe((res: any) => {
      console.log(res);
      this.banks = res.danhSachNganHang;
    });
  }

  getLichSuGiaoDichByAdmin() {
    if (this.start > this.end) {
      this.showErorrDialog('Ngày bắt đầu phải trước ngày kết thúc');
      return;
    }

    const startTN = this.datepipe.transform(this.start, 'dd/MM/yyyy');
    const endTN = this.datepipe.transform(this.end, 'dd/MM/yyyy');
    const value = {
      bank: this.selectedBank,
      ngaybatdau: startTN,
      ngayketthuc: endTN
    };
    this.lichsugiaodichService.getLichSuGiaoDichByAdmin(value).subscribe((res: any) => {
      console.log(res);
      if(res.active === '1') {
        this.showErorrDialog('Không tìm thấy tài khoản. Vui lòng kiểm tra lại email hoặc số điện thoại');
        this.dataSource.data = [];
        return;
      } else if(res.length === 0) {
        this.showErorrDialog('Chưa có giao dịch nào với ngân hàng này');
        this.dataSource.data = [];
        return;
      } else {
        this.dataSource.data = res;
      }
    });
  }

  getLichSuGiaoDichByAdminDetails(ma: number) {
    this.matDialog.open(DialogAdminlichsugiaodichComponent, {
      disableClose: true,
      height: '700px',
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
