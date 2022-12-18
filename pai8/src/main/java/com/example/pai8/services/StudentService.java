package com.example.pai8.services;

import com.example.pai8.converters.StudentConverter;
import com.example.pai8.converters.StudentMapper;
import com.example.pai8.dtos.StudentDto;
import com.example.pai8.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentService implements IStudentService {
    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;
    private final StudentMapper studentMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::mapStudentToStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getAllStudentsConverter() {

        return studentRepository.findAll().stream()
                .map(studentConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getAllStudentsWithoutAttachments() {
        return studentRepository.findAllWithoutAttachments();
    }
}
