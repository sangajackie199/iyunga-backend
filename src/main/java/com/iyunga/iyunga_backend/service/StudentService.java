package com.iyunga.iyunga_backend.service;

import com.iyunga.iyunga_backend.model.Student;
import com.iyunga.iyunga_backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    public boolean studentIdExists(String studentId) {
    return studentRepository.existsByStudentId(studentId);
}
public boolean studentIdExistsForAnotherStudent(String studentId, Long id) {
    return studentRepository.existsByStudentIdAndIdNot(studentId, id);
}

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}