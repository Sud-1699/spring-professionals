import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConstant } from '../app.constant';
import { Student } from '../models/student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }

  public getStudents(apiName: string): Observable<Response> {
    return this.http.get<Response>(AppConstant.BASE_URL + apiName);
  }

  public addStudent(apiName: string, student: Student): Observable<Response> {
    return this.http.post<Response>(AppConstant.BASE_URL + apiName, student);
  }

  public updateStudent(apiName: string, student: Student): Observable<Response> {
    return this.http.put<Response>(AppConstant.BASE_URL + apiName, student);
  }

  public deleteStudent(apiName: string, studentId: string): Observable<Response> {
    return this.http.delete<Response>(AppConstant.BASE_URL + apiName + "/" + studentId);
  }
}
