package com.iyunga.iyunga_backend.repository;

import com.iyunga.iyunga_backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
boolean existsByStudentId(String studentId);
boolean existsByStudentIdAndIdNot(String studentId, Long id);
}