package com.golf.app.service;

import com.golf.app.model.Course;

import java.util.Optional;

public interface CourseService {

    Iterable<Course> getAllCourses();
    Optional<Course> getCourseById(Long id);
    Optional<Course> getCourseByName(String name);
    Course saveCourse(Course course);
    Course updateCourse(Course course);
    void deleteCourse(Long id);
}
