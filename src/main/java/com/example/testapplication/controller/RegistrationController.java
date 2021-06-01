package com.example.testapplication.controller;

import com.example.testapplication.entity.Role;
import com.example.testapplication.entity.Users;
import com.example.testapplication.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Controller
public class RegistrationController {

    @Autowired
    UsersRepo usersRepo;

    @Value("${file.upload-dir}")
    private String uploadPath;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }


    //Регистрация с добавлением аватарки

    @PostMapping("/registration")
    public String addNewUser(@RequestParam ("file") MultipartFile file, Users user, Map<String, Object> model) throws IOException {
        Users userFromDb = usersRepo.findByUsername(user.getUsername());
        if (file != null && !file.isEmpty()) {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            //Добавление уникального ключа в имени файла
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            //Сохранение файла на сервере
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            //Запись в БД по уникальному имени
            user.setAvatar(resultFilename);
        }

        if (userFromDb != null) {
            model.put("warningMessage", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        usersRepo.save(user);

        return "redirect:/login";
    }
}
