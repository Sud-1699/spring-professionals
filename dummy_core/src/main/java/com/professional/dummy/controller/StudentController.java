package com.professional.dummy.controller;

import com.professional.dummy.enums.HttpStatusCode;
import com.professional.dummy.model.Response;
import com.professional.dummy.model.Student;
import com.professional.dummy.model.StudentCourse;
import com.professional.dummy.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllStudents() throws Exception {
        Response response = new Response<>();
        Map<String, Object> responseMap = new HashMap<>();
        try {
            List<Student> students = studentService.getAllStudents();
            if (students == null) {
                response.setMessage("No students found");
                response.setHttpStatus(HttpStatus.OK);
                response.setHttpStatusCode(HttpStatusCode.OK.getStatusCode());
                response.setData(null);
                responseMap.put("response", response);
                return new ResponseEntity(responseMap, HttpStatus.OK);
            }
            response.setMessage("Successfully fetch all students");
            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(HttpStatusCode.OK.getStatusCode());
            response.setData(students);
            responseMap.put("response", response);
            return new ResponseEntity(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Error while fetching all students");
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(HttpStatusCode.INTERNAL_SYSTEM_ERROR.getStatusCode());
            responseMap.put("response", response);
            return new ResponseEntity(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Map<String, Object>> getStudentById(@PathVariable("id") String studentId) throws Exception {
        Response response = new Response<>();
        Map<String, Object> responseMap = new HashMap<>();
        try {
            Student student = studentService.getStudentById(studentId);
            if(student == null) {
                response.setMessage("No students found");
                response.setHttpStatus(HttpStatus.OK);
                response.setHttpStatusCode(HttpStatusCode.OK.getStatusCode());
                response.setData(null);
                responseMap.put("response", response);
                return new ResponseEntity(responseMap, HttpStatus.OK);
            }
            response.setMessage("One Student found");
            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(HttpStatusCode.OK.getStatusCode());
            response.setData(student);
            responseMap.put("response", response);
            return new ResponseEntity(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Error finding student");
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(HttpStatusCode.INTERNAL_SYSTEM_ERROR.getStatusCode());
            responseMap.put("response", response);
            return new ResponseEntity(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addStudent(@RequestBody @Validated Student student) throws Exception {
        Response response = new Response<>();
        Map<String, Object> responseMap = new HashMap<>();
        if(student == null) {
            response.setMessage("No Student found to add");
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setHttpStatusCode(HttpStatusCode.INVALID_REQUEST.getStatusCode());
            responseMap.put("response", response);
            return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
        }
        try {
            studentService.addStudent(student);
            response.setMessage("New student added");
            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(HttpStatusCode.OK.getStatusCode());
            responseMap.put("response", response);
            return new ResponseEntity(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(HttpStatusCode.INTERNAL_SYSTEM_ERROR.getStatusCode());
            response.setData(e.getCause());
            responseMap.put("response", response);
            return new ResponseEntity(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateStudent(@RequestBody Student student) throws Exception {
        Response response = new Response<>();
        Map<String, Object> responseMap = new HashMap<>();
        if(student == null) {
            response.setMessage("No Student found to add");
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setHttpStatusCode(HttpStatusCode.INVALID_REQUEST.getStatusCode());
            responseMap.put("response", response);
            return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
        }
        try {
            studentService.updateStudent(student);
            response.setMessage("One student updated");
            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(HttpStatusCode.OK.getStatusCode());
            responseMap.put("response", response);
            return new ResponseEntity(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(HttpStatusCode.INTERNAL_SYSTEM_ERROR.getStatusCode());
            response.setData(e.getCause());
            responseMap.put("response", response);
            return new ResponseEntity(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteStudentById(@PathVariable("id") String studentId) throws Exception {
        Response response = new Response<>();
        Map<String, Object> responseMap = new HashMap<>();
        if(studentId.isEmpty()) {
            response.setMessage("Student Id is empty");
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setHttpStatusCode(HttpStatusCode.INVALID_REQUEST.getStatusCode());
            responseMap.put("response", response);
            return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
        }
        try {
            studentService.deleteStudent(studentId);
            response.setMessage("One Student Deleted");
            response.setHttpStatus(HttpStatus.OK);
            response.setHttpStatusCode(HttpStatusCode.OK.getStatusCode());
            responseMap.put("response", response);
            return new ResponseEntity(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setHttpStatusCode(HttpStatusCode.INTERNAL_SYSTEM_ERROR.getStatusCode());
            response.setData(e.getCause());
            responseMap.put("response", response);
            return new ResponseEntity(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/studentCourse/{id}")
    public List<StudentCourse> getStudentCourse(@PathVariable("id") String studentId) throws Exception {
        return studentService.getAllStudentCourse(studentId);
    }
}
