package com.example.pai8.converters;

import com.example.pai8.domain.Student;
import com.example.pai8.dtos.StudentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StudentMapper {
    @Mapping(target = "street", source = "student.address.street")
    @Mapping(target = "city", source = "student.address.city")
    @Mapping(target = "state", source = "student.address.state")
    @Mapping(target = "zip", source = "student.address.zip")
    StudentDto mapStudentToStudentDto(Student student);
}
