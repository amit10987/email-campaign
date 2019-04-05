package com.ub.email.controller;

import com.ub.email.service.LoadDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    LoadDataService loadDataService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/loadData")
    public String loadData() {
        loadDataService.loadData();
        return "home";
    }
}
