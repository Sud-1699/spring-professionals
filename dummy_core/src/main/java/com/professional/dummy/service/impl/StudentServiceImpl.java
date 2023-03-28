package com.professional.dummy.service.impl;

import com.professional.dummy.exception.BadException;
import com.professional.dummy.model.Student;
import com.professional.dummy.model.StudentCourse;
import com.professional.dummy.repository.StudentRepository;
import com.professional.dummy.service.interfaces.StudentService;
import com.professional.dummy.utils.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final EmailValidator emailValidator;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, EmailValidator emailValidator) {
        this.studentRepository = studentRepository;
        this.emailValidator = emailValidator;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.selectAllStudents();
    }

    @Override
    public Student getStudentById(String studentId) {
        return studentRepository.selectStudentById(studentId);
    }

    @Override
    public void addStudent(Student student) {
        UUID newStudentId = Optional.ofNullable(student.getStudentId()).orElse(UUID.randomUUID());
        //TODO: Validate Email
        if(!emailValidator.test(student.getEmail())) {
           throw new BadException(student.getEmail() + " is not valid email");
        }
        //TODO: Verify email uniqueness
        if(studentRepository.isEmailUnique(student.getEmail())) {
            throw new BadException(student.getEmail() + " is already exits");
        }
        studentRepository.insertStudent(newStudentId, student);
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.updateStudent(student);
    }

    @Override
    public void deleteStudent(String studentId) {
        studentRepository.deleteStudent(studentId);
    }

    @Override
    public List<StudentCourse> getAllStudentCourse(String studentId) {
        return studentRepository.getAllStudentCourse(studentId);
    }
}