import { Component, OnInit, ViewChild, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatDialog } from '@angular/material';
import { TaiKhoanDangNhapService } from 'src/app/services/taikhoandangnhap.service';
import * as jwt_decode from 'jwt-decode';
import { WebStorageSerivce } from 'src/app/services/webstorage.service';
import { DialogErrorsComponent } from 'src/app/share/dialog-errors/dialog-errors.component';
import { Subject, Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap, flatMap } from 'rxjs/operators';
import { SearchService } from 'src/app/services/search.service';

export interface PhanTu {
  mataikhoanthanhtoan: number;
  hoten: string;
  sodu: string;
  trangthai: number;
}

@Component({
  selector: 'app-taikhoanthanhtoan',
  templateUrl: './taikhoanthanhtoan.component.html',
  styleUrls: ['./taikhoanthanhtoan.component.scss']
})

export class TaikhoanthanhtoanComponent implements OnInit {
  private searchTerms = new Subject<string>();
  list$: Observable<any>;
  Show = true;
  maTaikhoan = '';

  searchResult: any[] = [];

  displayedColumns: string[] = ['mataikhoanthanhtoan', 'hoten', 'sodu', 'trangthai'];
  dataSource = new MatTableDataSource();
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(
    private taiKhoanDangNhapService: TaiKhoanDangNhapService,
    private webStorageSerivce: WebStorageSerivce,
    private searchService: SearchService,
    private matDialog: MatDialog,
    private changeDetectorRefs: ChangeDetectorRef
  ) { }

  search(term: string): void {
    if (term === '') {
      // this.searchTerms.next(term);
      this.Show = false;
      this.maTaikhoan = term;
      this.searchResult = [];
      this.getAccount();
      console.log(term);

    } else {
      this.searchTerms.next(term);
      this.Show = true;
      this.maTaikhoan = term;
      console.log(term);
    }
  }

  ngOnInit() {
    this.getAccount();
    this.dataSource.paginator = this.paginator;
    this.list$ = this.searchTerms.pipe(
      debounceTime(500),
      distinctUntilChanged(),
      switchMap((term: string) => this.searchService.searchTaiKhoanThanhToan(term))

    );
    this.list$.subscribe((res: any) => {
      if (res === undefined || res.length === 0) {
        this.searchResult = [];

      } else {
        this.searchResult = res;
      }
    })
  }

  getAccount() {
    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    const value = {
      mataikhoan: decodeAT.mataikhoan
    }
    // this.searchService.searchTaiKhoanThanhToan('100001').subscribe((res: any) => {
    //   console.log(res);
    // });

    this.taiKhoanDangNhapService.findAllDetailsTaiKhoanDangNhap(value).subscribe((res: any) => {
      console.log(res);
      if (res.active === '1') {
        this.dataSource.data = [];
        this.showErorrDialog('Tài khoản thanh toán hiện chưa có. Vui lòng liên hệ với nhân viên');

      } else {
        res.DanhSachTaiKhoan.forEach(element => {
          if (element.trangthai === 1) {
            element.trangthai = 'Đang hoạt động';
          }
        });
        this.dataSource.data = res.DanhSachTaiKhoan;
      }

    })
  }

  fill(name: string) {
    this.maTaikhoan = name;
    this.Show = false;
    this.searchTerms.next(null);
    this.dataSource.data = this.searchResult;

  }

  /**
   * Tim kiem tu dong ko can button, click vao danh sach goi y se load len tai khoan can tim
   */

  showErorrDialog(text: any) {
    this.matDialog.open(DialogErrorsComponent, {
      disableClose: true,
      height: '300px',
      width: '300px',
      data: { message: text }
    });
  }

}
