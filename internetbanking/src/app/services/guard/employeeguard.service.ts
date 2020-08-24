import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthenticateService } from '../authenticate.service';
import { WebStorageSerivce } from '../webstorage.service';
import { Observable } from 'rxjs';
import * as jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class EmployeeguardService implements CanActivate {

  constructor(
    private router: Router,
    private authenticateService: AuthenticateService,
    private webStorageSerivce: WebStorageSerivce,
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {

    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    if (decodeAT.role === 2) {
      return true;

    } else {
      this.webStorageSerivce.clearLocalStorage();
      this.router.navigate(['/**']);
    }
  }
}