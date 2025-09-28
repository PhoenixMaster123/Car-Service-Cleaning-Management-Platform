package com.example.carservice.web.controller;

import com.example.carservice.user.service.UserService;
import com.example.carservice.web.dto.LoginRequest;
import com.example.carservice.web.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    public ModelAndView showLoginForm() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@Valid LoginRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("login");
        }

        userService.login(request);

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/register")
    public ModelAndView showRegistrationPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("registerRequest", new RegisterRequest());

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerNewUser(@Valid RegisterRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
           return new ModelAndView("register");
        }

        userService.register(request);

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/home")
    public ModelAndView showHomePage() {
       ModelAndView modelAndView = new ModelAndView();

       modelAndView.setViewName("index");

       return modelAndView;
    }
}
