package com.professional.dummy.repository;

import com.professional.dummy.dto.StudentDTO;
import com.professional.dummy.exception.BadException;
import com.professional.dummy.model.Student;
import com.professional.dummy.model.StudentCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class StudentRepository extends StudentDTO {

    private final JdbcTemplate jdbcTemplate;
    private String query = "";

    @Autowired
    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> selectAllStudents() {
        query = "SELECT " +
                "student_id, " +
                "first_name, " +
                "last_name, email, " +
                "gender " +
                "FROM student";
        try {
            return jdbcTemplate.query(query, getStudentRowMapper());
        } catch (DataAccessException e) {
            throw new BadException("Error in getting all data", e.getCause());
        }
    }

    public Student selectStudentById(String studentId) {
        query = "SELECT student_id," +
                "first_name," +
                "last_name," +
                "email, gender" +
                " FROM student WHERE student_id=?";
        try {
            return jdbcTemplate.queryForObject(query, getStudentRowMapper(), UUID.fromString(studentId));
        } catch (DataAccessException e) {
            throw new BadException("Error in getting single data", e.getCause());
        }
    }

    public void insertStudent(UUID studentId, Student student) {
        query = "INSERT INTO " +
                "student (" +
                "student_id, " +
                "first_name, " +
                "last_name, " +
                "email, " +
                "gender) " +
                "VALUES (?, ?, ?, ?, ?::gender)";
        try {
            jdbcTemplate.update(query, studentId, student.getFirstName(),
                    student.getLastName(), student.getEmail(),
                    student.getGender());
        } catch (DataAccessException e) {
            throw new BadException("Error in adding data", e.getCause());
        }
    }

    public boolean isEmailUnique(String email) {
        query = "SELECT EXISTS ( " +
                "SELECT 1 " +
                "FROM student " +
                "WHERE email=? " +
                ")";

        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(query, new Object[] {email},
                (rs, i) -> rs.getBoolean(1)));
    }

    public void updateStudent(Student student) {
        query = "UPDATE student " +
                "SET first_name=?, " +
                "last_name=?, email=?, " +
                "gender=? WHERE student_id=?";

        try {
            jdbcTemplate.update(query, student.getFirstName(), student.getLastName(),
                    student.getEmail(), student.getGender(),
                    student.getStudentId());
        } catch (DataAccessException e) {
            throw new BadException("Error in updating data", e.getCause());
        }

    }

    public void deleteStudent(String studentId) {
        query = "DELETE FROM student WHERE student_id=?";

        try {
            jdbcTemplate.update(query, UUID.fromString(studentId));
        } catch (DataAccessException e) {
            throw new BadException("Error in deleting data", e.getCause());
        }
    }

    public List<StudentCourse> getAllStudentCourse(String studentId) {
        query = "SELECT " +
                "s.student_id, " +
                "c.course_id, " +
                "c.name, " +
                "c.description, " +
                "c.department, " +
                "c.teacher_name, " +
                "sc.start_date, " +
                "sc.end_date, " +
                "sc.grade " +
                "FROM student s " +
                "JOIN student_course sc USING(student_id) " +
                "JOIN course c USING(course_id) " +
                "WHERE s.student_id=?";
        try {
            return jdbcTemplate.query(query, getStudentCourseRowMapper(), UUID.fromString(studentId));
        } catch (DataAccessException e) {
            throw new BadException("Error in getting all data", e.getCause());
        }
    }
}
