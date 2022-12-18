package com.example.pai8.converters;

import com.example.pai8.domain.Student;
import com.example.pai8.dtos.StudentDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter implements Converter<Student, StudentDto> {
    @Override
    public StudentDto convert(Student source) {
        return StudentDto.builder()
                .name(source.getName())
                .surname(source.getSurname())
                .age(source.getAge())
                .street(source.getAddress().getStreet())
                .city(source.getAddress().getCity())
                .state(source.getAddress().getState())
                .zip(source.getAddress().getZip())
                .build();
    }
}
