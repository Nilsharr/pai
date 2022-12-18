package com.example.pai8.controllers;

import com.example.pai8.dtos.StudentDto;
import com.example.pai8.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/converter")
    public List<StudentDto> getAllStudentsConverter() {
        return studentService.getAllStudentsConverter();
    }

    @GetMapping(value = "/repo")
    public List<StudentDto> getAllStudentsRepo() {
        return studentService.getAllStudentsWithoutAttachments();
    }
}
