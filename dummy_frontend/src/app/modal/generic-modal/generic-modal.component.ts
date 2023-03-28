import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AppConstant } from 'src/app/app.constant';
import { Modal } from 'src/app/models/modal';
import { Student } from 'src/app/models/student';

@Component({
  selector: 'app-generic-modal',
  templateUrl: './generic-modal.component.html',
  styleUrls: ['./generic-modal.component.scss']
})
export class GenericModalComponent implements OnInit {

  @Input() modalObj: any;
  @Output() sendObj = new EventEmitter();

  public isVisible: boolean = false;
  public modalType: string | undefined = "";
  public modalWidth: string = '350px'; //Defualt Width
  private modalData!: Student | string;
  public title: string = "";
  public genderValue!: string;
  public inputSpan: number = 25;

  constructor(public fb: FormBuilder) { }

  commonForm = this.fb.group({
    studentId: [''],
    firstName: ['', [Validators.required]],
    lastName: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    gender: ['', [Validators.required]]
  })

  ngOnInit(): void {
    this.showModal(this.modalObj);
  }

  showModal(data: Modal): void {
    this.isVisible = data.isVisible;
    this.modalType = data.type;
    this.modalWidth = data.width;
    this.modalData = data.data;
    if(this.modalType === AppConstant.modalCRUDAction.modalAdd) {
      this.title = "Add New Student";
    } else if(this.modalType === AppConstant.modalCRUDAction.modalEdit) {
      this.title = "Edit Student Record";
      const student = data.data;
      this.commonForm.patchValue({
        studentId: student.studentId,
        firstName: student.firstName,
        lastName: student.lastName,
        email: student.email,
        gender: student.gender
      });
    } else {
      this.title = "Delete Student Record";
    }
  }

  handleOk(): void {
    this.isVisible = false;
    this.sendObj.emit({
      isVisible: this.isVisible,
      CRUDAction: this.modalType,
      data: (this.modalType !== AppConstant.modalCRUDAction.modalDelete) ? this.commonForm.value : this.modalData
    });
  }

  handleCancel(): void {
    this.isVisible = false;
    this.sendObj.emit({
      isVisible: this.isVisible,
      data: "Cancel"
    });
  }

}
