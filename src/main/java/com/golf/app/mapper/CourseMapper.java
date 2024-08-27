package com.golf.app.mapper;

import com.golf.app.dto.CourseDto;
import com.golf.app.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course course(CourseDto courseDto);
    CourseDto courseDto(Course course);
}
