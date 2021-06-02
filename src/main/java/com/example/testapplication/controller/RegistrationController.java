package com.example.testapplication.controller;

import com.example.testapplication.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @Autowired
    UsersRepo usersRepo;

    @Value("${file.upload-dir}")
    private String uploadPath;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    //Регистрация с добавлением аватарки




}
