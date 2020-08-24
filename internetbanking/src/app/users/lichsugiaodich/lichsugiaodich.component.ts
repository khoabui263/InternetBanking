import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { DatePipe } from '@angular/common';


export interface PeriodicElement {
  taikhoannguoigui: string;
  taikhoannguoinhan: string;
  sotien: string;
  manganhanggui: string;
  manganhangnhan: string;
  ngayGiaoDich: string;
  signature: string;

}

const ELEMENT_DATA: PeriodicElement[] = [
  // tslint:disable-next-line: max-line-length
  {taikhoannguoigui: '200001', taikhoannguoinhan: '200001', sotien: '500000', manganhanggui: 'RSA', manganhangnhan: 'Local', ngayGiaoDich: '1595989283000', signature: 'stringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstring'},
  // tslint:disable-next-line: max-line-length
  {taikhoannguoigui: '200001', taikhoannguoinhan: '200001', sotien: '500000', manganhanggui: 'RSA', manganhangnhan: 'Local', ngayGiaoDich: '1595989283000', signature: 'stringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstring'},
  // tslint:disable-next-line: max-line-length
  {taikhoannguoigui: '200001', taikhoannguoinhan: '200001', sotien: '500000', manganhanggui: 'RSA', manganhangnhan: 'Local', ngayGiaoDich: '1595989283000', signature: 'stringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstring'},
  // tslint:disable-next-line: max-line-length
  {taikhoannguoigui: '200001', taikhoannguoinhan: '200001', sotien: '500000', manganhanggui: 'RSA', manganhangnhan: 'Local', ngayGiaoDich: '1595989283000', signature: 'stringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstring'},
  // tslint:disable-next-line: max-line-length
  {taikhoannguoigui: '200001', taikhoannguoinhan: '200001', sotien: '500000', manganhanggui: 'RSA', manganhangnhan: 'Local', ngayGiaoDich: '1595989283000', signature: 'stringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstring'},
  // tslint:disable-next-line: max-line-length
  {taikhoannguoigui: '200001', taikhoannguoinhan: '200001', sotien: '500000', manganhanggui: 'RSA', manganhangnhan: 'Local', ngayGiaoDich: '1595989283000', signature: 'stringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstring'},
  // tslint:disable-next-line: max-line-length
  {taikhoannguoigui: '200001', taikhoannguoinhan: '200001', sotien: '500000', manganhanggui: 'RSA', manganhangnhan: 'Local', ngayGiaoDich: '1595989283000', signature: 'stringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstring'},
  // tslint:disable-next-line: max-line-length
  {taikhoannguoigui: '200001', taikhoannguoinhan: '200001', sotien: '500000', manganhanggui: 'RSA', manganhangnhan: 'Local', ngayGiaoDich: '1595989283000', signature: 'stringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstring'},
  // tslint:disable-next-line: max-line-length
  {taikhoannguoigui: '200001', taikhoannguoinhan: '200001', sotien: '500000', manganhanggui: 'RSA', manganhangnhan: 'Local', ngayGiaoDich: '1595989283000', signature: 'stringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstring'},
  // tslint:disable-next-line: max-line-length
  {taikhoannguoigui: '200001', taikhoannguoinhan: '200001', sotien: '500000', manganhanggui: 'RSA', manganhangnhan: 'Local', ngayGiaoDich: '1595989283000', signature: 'stringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstring'},
  // tslint:disable-next-line: max-line-length
  {taikhoannguoigui: '200001', taikhoannguoinhan: '200001', sotien: '500000', manganhanggui: 'RSA', manganhangnhan: 'Local', ngayGiaoDich: '1595989283000', signature: 'stringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstring'},
  // tslint:disable-next-line: max-line-length
  {taikhoannguoigui: '200001', taikhoannguoinhan: '200001', sotien: '500000', manganhanggui: 'RSA', manganhangnhan: 'Local', ngayGiaoDich: '1595989283000', signature: 'stringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstring'},
  // tslint:disable-next-line: max-line-length
  {taikhoannguoigui: '200001', taikhoannguoinhan: '200001', sotien: '500000', manganhanggui: 'RSA', manganhangnhan: 'Local', ngayGiaoDich: '1595989283000', signature: 'stringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstring'},
  // tslint:disable-next-line: max-line-length
  {taikhoannguoigui: '200001', taikhoannguoinhan: '200001', sotien: '500000', manganhanggui: 'RSA', manganhangnhan: 'Local', ngayGiaoDich: '1595989283000', signature: 'stringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstring'},
  // tslint:disable-next-line: max-line-length
  {taikhoannguoigui: '200001', taikhoannguoinhan: '200001', sotien: '500000', manganhanggui: 'RSA', manganhangnhan: 'Local', ngayGiaoDich: '1595989283000', signature: 'stringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstring'},
  // tslint:disable-next-line: max-line-length
  {taikhoannguoigui: '200001', taikhoannguoinhan: '200001', sotien: '500000', manganhanggui: 'RSA', manganhangnhan: 'Local', ngayGiaoDich: '1595989283000', signature: 'stringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstringstring'},
];

@Component({
  selector: 'app-lichsugiaodich',
  templateUrl: './lichsugiaodich.component.html',
  styleUrls: ['./lichsugiaodich.component.scss']
})
export class LichsugiaodichComponent implements OnInit {
  // tslint:disable-next-line: max-line-length
  displayedColumns: string[] = ['taikhoannguoigui', 'taikhoannguoinhan', 'sotien', 'manganhanggui', 'manganhangnhan', 'ngayGiaoDich', 'signature'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  start = new Date(new Date().setDate(new Date().getDate() - 30));
  end = new Date();

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
  }

  constructor(public datepipe: DatePipe){}

  chuyen(){
    // console.log(new Date(this.start.getTime() - this.start.getTimezoneOffset() * 60 * 1000));
    // console.log(this.start.toString());
    const latestDate = this.datepipe.transform(this.start, 'dd/MM/yyyy');
    console.log(latestDate);
    console.log(typeof(latestDate));

  }

}
