import { Component, OnInit } from '@angular/core';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AppConstant } from 'src/app/app.constant';
import { Modal } from 'src/app/models/modal';
import { Student } from 'src/app/models/student';
import { StudentService } from 'src/app/services/student.service';

declare const require: any;
const randomColor = require('randomcolor');

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.scss']
})
export class StudentComponent implements OnInit {

  public isLoaded: boolean = false;
  public students: Student[] = [];
  public showModal: boolean = false;
  public modalObj!: Modal;

  constructor(private studentService: StudentService, private notification: NzNotificationService) { }

  ngOnInit(): void {
    this.getStudents();
  }

  private getStudents() {
    this.studentService.getStudents(AppConstant.getAllStudentsAPI)
      .subscribe((result: any) => {
        this.students = result.response.data;
        this.students.map((student, index) => {
          student.avatarColor = randomColor();
        });
        this.isLoaded = true;
      },
      error => {
        console.error(`Get All Students: `, error);
      });
  }

  //Modal Service
  public addStudentModal(): void {
    console.log("Add Student");
    this.showModal = true;
    this.modalObj = {
      isVisible: this.showModal,
      type: AppConstant.modalCRUDAction.modalAdd,
      width: '350px'
    }
  }

  public editStudentModal(student: Student): void {
    console.log(`Student: `, student);
    
    this.showModal = true;
    this.modalObj = {
      isVisible: this.showModal,
      type: AppConstant.modalCRUDAction.modalEdit,
      data: student,
      width: '350px'
    }
  }

  public deleteStudentModal(studentId: string): void {
    this.showModal = true;
    this.modalObj = {
      isVisible: this.showModal,
      type: AppConstant.modalCRUDAction.modalDelete,
      data: studentId,
      width: '200px'
    }
  }

  //Modal Event Service
  public modalService(modalEvent: Modal): void {
    console.log(`Modal Data: `, modalEvent);
    if(modalEvent) {
      this.showModal = modalEvent.isVisible;
      if(modalEvent.data !== AppConstant.modalUIAction.modalCancel) {
        console.log(modalEvent.data);
        if(modalEvent.CRUDAction === AppConstant.modalCRUDAction.modalAdd) {
          this.addStudent(modalEvent.data).then(resolve => {
            if(resolve) {
              this.getStudents();
            }
          });
        } else if(modalEvent.CRUDAction === AppConstant.modalCRUDAction.modalEdit) {
          this.updateStudent(modalEvent.data).then(resolve => {
            if(resolve) {
              this.getStudents();
            }
          }).catch(reject => {
            this.notification.create(
              'error',
              reject.httpStatus,
              reject.message
            );
          });
        } else {
          this.deleteStudent(modalEvent.data).then(resolve => {
            if(resolve) {
              this.getStudents();
            }
          });
        }
      }
    }
  }

  //CRUD API Service
  private addStudent(student: Student) {
    return new Promise((resolve, reject) => {
      this.studentService.addStudent(AppConstant.addStudentAPI, student).subscribe(
        response => {
          console.log(`Add Student Response: `, response);
          resolve(true);
        },
        error => {
          console.error(`Add Student Error: `, error.error.response);
          reject(error.error.response);
        }
      );
    });
  }

  private updateStudent(student: Student) {
    return new Promise((resolve, reject) => {
      this.studentService.updateStudent(AppConstant.updateStudentAPI, student).subscribe(
        response => {
          console.log(`Update Student: `, response);
          resolve(true);
        },
        error => {
          console.error(`Update Error: `, error.error.response);
          reject(error.error.response);
        }
      );
    });
  }

  private deleteStudent(studentId: string) {
    return new Promise((resolve, reject) => {
      this.studentService.deleteStudent(AppConstant.deleteStudentAPI, studentId).subscribe(
        response => {
          console.log(`Delete Student: `, response);
          resolve(true);
        },
        error => {
          console.error(`Delete Error: `, error.error.response);
          reject(error.error.response);
        }
      );
    });
  }
}
