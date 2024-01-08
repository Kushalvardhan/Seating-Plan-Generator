package com.shuffle.Generator.repository;

import com.shuffle.Generator.model.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByBranch(String sBranch);

    Student findByIdNumber(String sId);

    List<Student> findByExamHall(int hNo, Sort sort);
}
