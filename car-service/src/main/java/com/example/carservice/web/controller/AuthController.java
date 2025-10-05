package com.example.carservice.web.controller;

import com.example.carservice.user.model.User;
import com.example.carservice.user.service.UserService;
import com.example.carservice.web.dto.LoginRequest;
import com.example.carservice.web.dto.RegisterRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showIndexPage() {
        return "/public/index";
    }

    @GetMapping("/login")
    public ModelAndView showLoginForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/auth/login");
        modelAndView.addObject("loginRequest", new LoginRequest());

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@Valid LoginRequest request, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("/auth/login");
        }

        User user = userService.login(request);

        session.setAttribute("userId", user.getId());

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/register")
    public ModelAndView showRegistrationPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/auth/register");
        modelAndView.addObject("registerRequest", new RegisterRequest());

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerNewUser(@Valid RegisterRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
           return new ModelAndView("/auth/register");
        }

        userService.register(request);

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/home")
    public ModelAndView showHomePage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);
        modelAndView.addObject("user", user);

       modelAndView.setViewName("public/index");

       return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/home";
    }
}
