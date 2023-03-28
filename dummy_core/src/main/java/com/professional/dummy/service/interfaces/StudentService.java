package com.professional.dummy.service.interfaces;

import com.professional.dummy.model.Student;
import com.professional.dummy.model.StudentCourse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentService {

    List<Student> getAllStudents();
    Student getStudentById(String studentId);
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(String studentId);
    List<StudentCourse> getAllStudentCourse(String studentId);
}
