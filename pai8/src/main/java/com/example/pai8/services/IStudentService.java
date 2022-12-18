package com.example.pai8.services;

import com.example.pai8.dtos.StudentDto;

import java.util.List;

public interface IStudentService {
    List<StudentDto> getAllStudents();

    List<StudentDto> getAllStudentsConverter();

    List<StudentDto> getAllStudentsWithoutAttachments();
}
