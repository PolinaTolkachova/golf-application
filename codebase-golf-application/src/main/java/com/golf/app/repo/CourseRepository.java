package com.golf.app.repo;

import com.golf.app.model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    Optional<Course> findById(Long id);
    Optional<Course> findByName(String name);
}
