package com.professional.dummy.dto;

import com.professional.dummy.model.Student;
import com.professional.dummy.model.StudentCourse;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public class StudentDTO {

    public RowMapper<Student> getStudentRowMapper() {
        return (rs, rowNum) -> {
            UUID studentId = UUID.fromString(rs.getString("student_id"));
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String gender = rs.getString("gender");

            return new Student(studentId, firstName, lastName, email, gender);
        };
    }

    public RowMapper<StudentCourse> getStudentCourseRowMapper() {
        return (rs, rowNum) -> {
            UUID studentId = UUID.fromString(rs.getString("student_id"));
            UUID courseId = UUID.fromString(rs.getString("course_id"));
            String name = rs.getString("name");
            String description = rs.getString("description");
            String department = rs.getString("department");
            String teacherName = rs.getString("teacher_name");
            LocalDate startDate = rs.getDate("start_date").toLocalDate();
            LocalDate endDate = rs.getDate("end_date").toLocalDate();
            Integer grade = Optional.ofNullable(rs.getString("grade"))
                    .map(Integer::parseInt)
                    .orElse(null);

            return new StudentCourse(studentId, courseId, name, description, department, teacherName, startDate, endDate, grade);
        };
    }
}
