package com.golf.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Main controller for the web application.
 * This controller is responsible for handling the requests for the main pages of the web application.
 */
@Controller
public class MainController {

    private static final String TITLE_ATTRIBUTE = "title";
    private static final String HOME_PAGE = "home";
    private static final String LOGIN_PAGE = "login";

    @GetMapping("/")
    public String displayHomePage(Model model) {
        model.addAttribute(TITLE_ATTRIBUTE, "GOLF Club");
        return HOME_PAGE;
    }

    @GetMapping("/login")
    public String displayLoginPage() {
        return LOGIN_PAGE;
    }
}