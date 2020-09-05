import { Component, OnInit, ViewChild } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { MatTableDataSource, MatPaginator, MatDialog } from '@angular/material';
import { TaiKhoanDangNhapService } from 'src/app/services/taikhoandangnhap.service';
import { WebStorageSerivce } from 'src/app/services/webstorage.service';
import { SearchService } from 'src/app/services/search.service';
import { debounceTime, distinctUntilChanged, switchMap, flatMap } from 'rxjs/operators';
import { element } from 'protractor';
import { DialogCapnhatnhanvienComponent } from '../dialogs/dialog-capnhatnhanvien/dialog-capnhatnhanvien.component';
import { DialogXoanhanvienComponent } from '../dialogs/dialog-xoanhanvien/dialog-xoanhanvien.component';

@Component({
  selector: 'app-quanlynhanvien',
  templateUrl: './quanlynhanvien.component.html',
  styleUrls: ['./quanlynhanvien.component.scss']
})
export class QuanlynhanvienComponent implements OnInit {
  private searchTerms = new Subject<string>();
  list$: Observable<any>;
  Show = true;
  email = '';
  searchResult: any[] = [];

  displayedColumns: string[] = ['hoten', 'email', 'sodienthoai', 'chinhsua'];
  dataSource = new MatTableDataSource();
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(
    private taiKhoanDangNhapService: TaiKhoanDangNhapService,
    private webStorageSerivce: WebStorageSerivce,
    private searchService: SearchService,
    private matDialog: MatDialog,
  ) { }

  search(term: string): void {
    if (term === '') {
      this.Show = false;
      this.email = term;
      this.searchResult = [];
      this.getAccount();
      console.log(term);

    } else {
      this.searchTerms.next(term);
      this.Show = true;
      this.email = term;
      console.log(term);
    }
  }

  ngOnInit() {
    this.getAccount();
    this.dataSource.paginator = this.paginator;
    this.list$ = this.searchTerms.pipe(
      debounceTime(500),
      distinctUntilChanged(),
      switchMap((term: string) => this.searchService.searchTaiKhoanNhanVien(term))

    );
    this.list$.subscribe((res: any) => {
      if (res === undefined || res.length === 0) {
        this.searchResult = [];

      } else {
        this.searchResult = res;
      }
    });
  }

  getAccount() {
    this.taiKhoanDangNhapService.findAllEmployee().subscribe((res: any) => {
      console.log(res);
      this.dataSource.data = res;
    })
  }

  fill(email: string) {
    this.email = email;
    this.Show = false;
    const array = [];
    this.searchResult.forEach(element => {
      if (element.email === this.email) {
        array.push(element);
      }
    })
    this.dataSource.data = array;
  }

  update(id, name, mail, sdt) {
    const updated = this.matDialog.open(DialogCapnhatnhanvienComponent, {
      disableClose: true,
      height: '500px',
      width: '550px',
      data: {
        mataikhoan: id,
        hoten: name,
        email: mail,
        sodienthoai: sdt
      }
    });

    updated.afterClosed().subscribe(() => {
      this.getAccount();
    });
  }

  delete(id: any) {
    const deleted = this.matDialog.open(DialogXoanhanvienComponent, {
      disableClose: true,
      height: '300px',
      width: '300px',
      data: {
        mataikhoan: id,
      }
    });

    deleted.afterClosed().subscribe(() => {
      this.getAccount();
    });
  }
}
