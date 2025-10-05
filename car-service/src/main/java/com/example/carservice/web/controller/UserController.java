package com.example.carservice.web.controller;

import com.example.carservice.user.model.User;
import com.example.carservice.user.service.UserService;
import com.example.carservice.web.dto.EditProfileRequest;
import com.example.carservice.web.mapper.DtoMapper;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public ModelAndView showDashboard(HttpSession session) {

        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/account/dashboard");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @GetMapping("/bookings")
    public ModelAndView showBookings(HttpSession session) {

        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/account/bookings");
        modelAndView.addObject("user", user);

        return modelAndView;
    }


    @GetMapping("{id}/edit-details")
    public ModelAndView showEditDetailsPage(@PathVariable UUID id) {

        User user = userService.getById(id);
        EditProfileRequest editProfileRequest = DtoMapper.fromUser(user);


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/account/edit-details");
        modelAndView.addObject("user", user);
        modelAndView.addObject("editProfileRequest", editProfileRequest);


        return modelAndView;
    }

    @PutMapping("{id}/profile")
    public ModelAndView updateProfile(@PathVariable UUID id, @Valid EditProfileRequest editProfileRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            User user = userService.getById(id);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("/account/edit-details");
            modelAndView.addObject("user", user);
            return modelAndView;
        }

        userService.updateProfile(id, editProfileRequest);

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/settings")
    public ModelAndView showSettings(HttpSession session) {

        UUID userId = (UUID) session.getAttribute("userId");
        User user = userService.getById(userId);

        ModelAndView modelAndView = new ModelAndView("account/settings");
        modelAndView.addObject("user", user);

        return modelAndView;
    }
}
