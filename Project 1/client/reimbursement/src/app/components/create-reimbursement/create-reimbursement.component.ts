import { Component, OnInit } from '@angular/core';
import {CreateReimbursementService} from '../services/create-reimbursement.service';
import {Globals} from '../../shared/Globals';
import {Router} from '@angular/router';
import {ReimbursementForm} from '../../shared/ReimbursementForm';
import {FileHolder} from 'angular2-image-upload';
import {FormGroup} from '@angular/forms';

@Component({
  selector: 'app-create-reimbursement',
  templateUrl: './create-reimbursement.component.html',
  providers: [CreateReimbursementService],
  styleUrls: ['./create-reimbursement.component.css']
})
export class CreateReimbursementComponent implements OnInit {
  category: number;
  amount: number;
  indicator = '';
  filesToUpload: Array<File>;

  constructor(private rs: CreateReimbursementService, public globals: Globals, private router: Router) {
  }

  onSubmit(): void {
    const fr = new FileReader();
    fr.onload = () => {
      console.log('READ');
      const rForm = new ReimbursementForm(this.amount, this.category,
        this.globals.username, this.globals.password, fr.result);
      this.rs.createReimbursement(rForm).subscribe(success => this.indicator = success ? 'Success!' : 'Add Failed',
        err => this.indicator = err['error']);
    }
    fr.readAsDataURL(this.filesToUpload[0]);

  }

  fileChangeEvent(fileInput: any) {
    this.filesToUpload = <Array<File>> fileInput.target.files;
    console.log(this.filesToUpload);
    console.log(this.filesToUpload[0]);

  }

  ngOnInit() {
  }

}
