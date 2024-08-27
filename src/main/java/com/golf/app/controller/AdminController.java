package com.golf.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final String ADMIN_PAGE = "admin";

    @GetMapping
    public String displayCourseMainPage() {
        return ADMIN_PAGE;
    }
}
