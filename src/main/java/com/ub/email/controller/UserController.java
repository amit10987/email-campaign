package com.ub.email.controller;

import com.ub.email.entity.User;
import com.ub.email.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {


    @Autowired
    UserRepository repository;

    @GetMapping("/user")
    public String user(Model model) {
        List<User> users = (List<User>) repository.findAll();
        model.addAttribute("users", users);
        return "user";
    }

    @PostMapping("/user/create")
    public String create(@ModelAttribute User user, Model model) {
        repository.save(user);
        return "redirect:/user";
    }

    @PostMapping("/user/delete")
    public String delete(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("user.id"));
        User user = new User();
        user.setId(id);
        repository.delete(user);
        return "redirect:/user";
    }
}
