package com.example.testapplication.controller;

import com.example.testapplication.entity.Users;
import com.example.testapplication.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    UsersRepo usersRepo;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("main")
    public String main(Map<String, Object> model) {
        Iterable<Users> users = usersRepo.findAll();
        model.put("users", users);
        return "main";

    }

    @GetMapping("changeStatus")
    public String statuses() {
        return "changeStatus";

    }
}
