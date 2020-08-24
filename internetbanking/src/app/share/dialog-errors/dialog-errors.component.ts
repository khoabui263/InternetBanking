import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-errors',
  templateUrl: './dialog-errors.component.html',
  styleUrls: ['./dialog-errors.component.scss']
})
export class DialogErrorsComponent implements OnInit {
  message = '';

  constructor(
    public dialogRef: MatDialogRef<DialogErrorsComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit() {
    this.message = this.data.message;
  }

  closeModal(){
    this.dialogRef.close();
  }

}
