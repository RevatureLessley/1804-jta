import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {MyRoutes} from './shared/app.routes';
import {Globals} from './shared/Globals';
import { EmployeeSplashComponent } from './components/employee-splash/employee-splash.component';
import { CreateReimbursementComponent } from './components/create-reimbursement/create-reimbursement.component';
import { UserInfoComponent } from './components/user-info/user-info.component';
import { ViewEmployeesComponent } from './components/view-employees/view-employees.component';
import { MyReimbursementsComponent } from './components/my-reimbursements/my-reimbursements.component';
import { AdminTableComponent } from './components/admin-table/admin-table.component';
import {ImageUploadModule} from 'angular2-image-upload';
import TableFilter from 'node_modules/tablefilter';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    EmployeeSplashComponent,
    CreateReimbursementComponent,
    UserInfoComponent,
    ViewEmployeesComponent,
    MyReimbursementsComponent,
    AdminTableComponent
  ],
  imports: [
    BrowserModule,
    ImageUploadModule.forRoot(),
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(MyRoutes)
  ],
  providers: [Globals],
  bootstrap: [AppComponent]
})
export class AppModule { }