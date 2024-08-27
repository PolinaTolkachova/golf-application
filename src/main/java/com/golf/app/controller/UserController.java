package com.golf.app.controller;

import com.golf.app.model.User;
import com.golf.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class UserController {

    private static final String REGISTRATION_PAGE = "registration/registration";

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String displayRegistrationPage() {
        return REGISTRATION_PAGE;
    }

    @PostMapping
    public String registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }
}
