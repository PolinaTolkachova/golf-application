package com.golf.app.controller;

import com.golf.app.dto.CourseDto;
import com.golf.app.model.Course;
import com.golf.app.service.CourseService;
import com.golf.app.utils.CourseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    private static final String COURSE_MAIN_PAGE = "course/course-main";
    private static final String COURSE_ADD_PAGE = "course/course-add";
    private static final String COURSES_ATTRIBUTE = "courses";
    private static final String REDIRECT_COURSE_MAIN_PAGE = "redirect:/course";

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String displayCourseMainPage(Model model) {
        model.addAttribute(COURSES_ATTRIBUTE, courseService.getAllCourses());
        return COURSE_MAIN_PAGE;
    }

    @GetMapping("/add")
    public String displayCourseAddPage() {
        return COURSE_ADD_PAGE;
    }

    @PostMapping("/add")
    public String addCourse(@ModelAttribute CourseDto courseDto) {
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setLocation(courseDto.getLocation());
        course.setHoles(CourseUtils.buildHoles(courseDto));
        courseService.saveCourse(course);

        return REDIRECT_COURSE_MAIN_PAGE;
    }
}
