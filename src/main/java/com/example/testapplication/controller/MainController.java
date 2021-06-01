package com.example.testapplication.controller;

import com.example.testapplication.entity.Users;
import com.example.testapplication.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UsersRepo usersRepo;

    @GetMapping("/")
    public String greeting(Map <String, Object> map){
        return "greeting";
    }


    @GetMapping("/main")
    public String main(Map <String, Object> model) {
        Iterable<Users> users = usersRepo.findAll();
        model.put("users", users);
        return "main";
    }

 /*   @PostMapping("/main")
    public String addNewUser(@RequestParam String username, @RequestParam String email, Map<String, Object> map) {
        Users user = new Users(username, email);
        usersRepo.save(user);
        Iterable<Users> users = usersRepo.findAll();
        map.put("users", users);
        return "main";
    }
*/
    @PostMapping("deleteAll")
    public String deleteAll(Map<String, Object> map){
        Iterable<Users> users = usersRepo.findAll();
        usersRepo.deleteAll(users);
        map.put("users", users);
        return "redirect:/";
    }

    @PostMapping("findById")
    public String findById(Integer id, Map<String, Object> map){
        Iterable<Users> users;
        if (id != null){
            users = usersRepo.findById(id);
        } else {
            users = usersRepo.findAll();
        }
        map.put("users", users);
        return "main";
    }

}
