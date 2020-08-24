import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import * as jwt_decode from 'jwt-decode';
import { Router } from '@angular/router';
import { WebStorageSerivce } from '../webstorage.service';
import { AuthenticateService } from '../authenticate.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminguardService implements CanActivate {

  constructor(
    private router: Router,
    private authenticateService: AuthenticateService,
    private webStorageSerivce: WebStorageSerivce,
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {

    const decodeAT = jwt_decode(this.webStorageSerivce.getLocalStorage('token-info').accessToken);
    if (decodeAT.role === 1) {
      return true;

    } else {
      this.webStorageSerivce.clearLocalStorage();
      this.router.navigate(['/**']);
    }
  }
}
