package com.example.carservice.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagesController {

    @GetMapping("/about")
    public ModelAndView showAboutUsPage() {
        return new ModelAndView("/public/about");
    }

    @GetMapping("/careers")
    public ModelAndView showCareersPage() {
        return new ModelAndView("/public/careers");
    }

    @GetMapping("/locations")
    public ModelAndView showLocationsPage() {
        return new ModelAndView("/public/locations");
    }

    @GetMapping("/news")
    public ModelAndView showNewsPage() {
        return new ModelAndView("public/news");
    }
}
