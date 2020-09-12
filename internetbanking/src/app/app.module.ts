import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { RecaptchaModule } from 'ng-recaptcha';
import { LoginComponent } from './login/login.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { NgxCurrencyModule } from 'ngx-currency';
import { DialogNoticeComponent } from './share/dialog-notice/dialog-notice.component';
import { DialogErrorsComponent } from './share/dialog-errors/dialog-errors.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorService } from './services/interceptor.service';
import { SearchService } from './services/search.service';
import { DialogReminderComponent } from './users/dialogs/dialog-reminder/dialog-reminder.component';
import { DialogActivegoinhoComponent } from './users/dialogs/dialog-activegoinho/dialog-activegoinho.component';
import { DialogUpdategoinhoComponent } from './users/dialogs/dialog-updategoinho/dialog-updategoinho.component';
import { DialogDeletegoinhoComponent } from './users/dialogs/dialog-deletegoinho/dialog-deletegoinho.component';
import { DialogUpdatenhacnoComponent } from './users/dialogs/dialog-updatenhacno/dialog-updatenhacno.component';
import { DialogThanhtoannoComponent } from './users/dialogs/dialog-thanhtoanno/dialog-thanhtoanno.component';
import { DialogThongbaonoComponent } from './users/dialogs/dialog-thongbaono/dialog-thongbaono.component';
import { DialogEmployeelichsugiaodichComponent } from './employees/dialogs/dialog-employeelichsugiaodich/dialog-employeelichsugiaodich.component';
import { DialogCapnhatnhanvienComponent } from './admin/dialogs/dialog-capnhatnhanvien/dialog-capnhatnhanvien.component';
import { DialogXoanhanvienComponent } from './admin/dialogs/dialog-xoanhanvien/dialog-xoanhanvien.component';
import { DialogAdminlichsugiaodichComponent } from './admin/dialogs/dialog-adminlichsugiaodich/dialog-adminlichsugiaodich.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ForgetPasswordComponent,
    NotFoundComponent,
    DialogNoticeComponent,
    DialogErrorsComponent,
    DialogReminderComponent,
    DialogActivegoinhoComponent,
    DialogUpdategoinhoComponent,
    DialogDeletegoinhoComponent,
    DialogUpdatenhacnoComponent,
    DialogThanhtoannoComponent,
    DialogThongbaonoComponent,
    DialogEmployeelichsugiaodichComponent,
    DialogCapnhatnhanvienComponent,
    DialogXoanhanvienComponent,
    DialogAdminlichsugiaodichComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule,
    NgxCurrencyModule,
    RecaptchaModule.forRoot(),
  ],
  entryComponents: [
    DialogNoticeComponent,
    DialogErrorsComponent,
    DialogReminderComponent,
    DialogActivegoinhoComponent,
    DialogUpdategoinhoComponent,
    DialogDeletegoinhoComponent,
    DialogUpdatenhacnoComponent,
    DialogThanhtoannoComponent,
    DialogThongbaonoComponent,
    DialogEmployeelichsugiaodichComponent,
    DialogCapnhatnhanvienComponent,
    DialogXoanhanvienComponent,
    DialogAdminlichsugiaodichComponent
  ],
  providers: [
    DatePipe,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    },
    SearchService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
