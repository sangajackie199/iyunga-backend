package com.iyunga.iyunga_backend.controller;

import com.iyunga.iyunga_backend.model.Student;
import com.iyunga.iyunga_backend.service.StudentService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/students")

public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

 @PostMapping
public ResponseEntity<?> addStudent(@Valid @RequestBody Student student) {

    if (studentService.studentIdExists(student.getStudentId())) {
        return ResponseEntity
                .badRequest()
                .body("Student ID already exists.");
    }

    return ResponseEntity.ok(studentService.saveStudent(student));
}

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
   @PutMapping("/{id}")
public ResponseEntity<?> updateStudent(
        @PathVariable Long id,
        @Valid @RequestBody Student student) {

    Student existingStudent = studentService.getStudentById(id);

    if (existingStudent == null) {
        return ResponseEntity.notFound().build();
    }

    if (studentService.studentIdExistsForAnotherStudent(
            student.getStudentId(), id)) {

        return ResponseEntity
                .badRequest()
                .body("Student ID already exists.");
    }

    existingStudent.setStudentName(student.getStudentName());
    existingStudent.setStudentId(student.getStudentId());
    existingStudent.setStudentClass(student.getStudentClass());
    existingStudent.setDateOfBirth(student.getDateOfBirth());
    existingStudent.setParentGuardianName(student.getParentGuardianName());
    existingStudent.setGender(student.getGender());
    existingStudent.setAddress(student.getAddress());
    existingStudent.setPhoneNumber(student.getPhoneNumber());

    return ResponseEntity.ok(
            studentService.saveStudent(existingStudent)
    );
}
@DeleteMapping("/{id}")
public String deleteStudent(@PathVariable Long id) {
    studentService.deleteStudent(id);
    return "Student deleted successfully";
}
}