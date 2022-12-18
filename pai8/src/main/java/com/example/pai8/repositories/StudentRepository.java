package com.example.pai8.repositories;

import com.example.pai8.domain.Student;
import com.example.pai8.dtos.StudentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT new com.example.pai8.dtos.StudentDto(s.name, s.surname, s.age, s.address.street, s.address.city, s.address.state, s.address.zip) FROM Student s")
    List<StudentDto> findAllWithoutAttachments();
}
