import { Router } from '@angular/router';
import { empty, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpErrorResponse, HttpClient } from '@angular/common/http';
import { HttpStatusCode } from '../globlas/enums';

@Injectable({
  providedIn: 'root'
})
export class HandleErrorService {
  constructor(
    public router: Router
  ) {
    this.handleError = this.handleError.bind(this);
    this.handleError = this.extractData.bind(this);
  }

  public extractData(res: any) {
    if (res.status !== HttpStatusCode.OK) {
      return this.checkServerError(res);
    }
    // Parse response data to json
    try {
      const body = res.body;
      return body || {};

    } catch (error) {
      //   this.dialogServiceService.showDialogError('message error');
      console.log('Message error');
      return empty();
    }

  }

  public checkServerError(res: HttpErrorResponse | any) {
    const statusCode = res.status;

    if (!statusCode) {
      return empty();
    }

    switch (statusCode) {
      // Bad gate way status
      case HttpStatusCode.BadGateway:
        console.log(`Message error code: ${statusCode}`);
        console.log(res.error)
        // this.dialogServiceService.showDialogError(`message error code: ${statusCode}`);
        // this.showDialogError(`${ERRORS.SERVER_ERROR}<br>CODE: ${statusCode}`);
        break;
      // Internal server error
      case HttpStatusCode.InternalServerError:
        console.log(`Message error code: ${statusCode}`);
        console.log(res.error)
        // this.dialogServiceService.showDialogError(`message error code: ${statusCode}`);
        break;
      // Not found
      case HttpStatusCode.NotFound:
        console.log(`Message error code: ${statusCode}`);
        console.log(res.error)
        // this.dialogServiceService.showDialogError(`message error code: ${statusCode}`);
        break;
      // Service unavailable
      case HttpStatusCode.ServiceUnavailable:
        console.log(`Message error code: ${statusCode}`);
        console.log(res.error)
        // this.dialogServiceService.showDialogError(`message error code: ${statusCode}`);
        break;
      // Client error
      case HttpStatusCode.Forbidden:
        console.log(`Message error code: ${statusCode}`);
        console.log(res.error)
        // this.dialogServiceService.showDialogError(`message error code: ${statusCode}`);
        break;
      // Bad request
      case HttpStatusCode.BadRequest:
        console.log(`Message error code: ${statusCode}`);
        console.log(res.error)
        // this.dialogServiceService.showDialogError(`message error code: ${statusCode}`);
        break;
      case HttpStatusCode.Unauthorized:
        console.log(`Message error code: ${statusCode}`);
        console.log(res.error)
        // this.router.navigateByUrl('/login');
        break;
      default:
        // Unexpected error
        console.log(`Message error code: Khong nhan dien dc loi`);
        console.log(res.error)
        // this.dialogServiceService.showDialogError(`message error`);
        break;
    }
    return empty();
  }


  public handleError(error) {
    console.log('Message error');
    // this.dialogServiceService.showDialogError(`message error`);
    return throwError(error);
  }
}
