package com.example.carservice.web.controller;

import com.example.carservice.service.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ServiceController {

    private final CatalogService serviceController;

    @Autowired
    public ServiceController(CatalogService serviceController) {
        this.serviceController = serviceController;
    }

    @GetMapping("/services")
    public ModelAndView showServicesPage() {
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.setViewName("public/services");

       return modelAndView;
    }
}
