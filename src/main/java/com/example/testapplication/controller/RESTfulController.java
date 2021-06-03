package com.example.testapplication.controller;

import com.example.testapplication.SavingOnHDD;
import com.example.testapplication.entity.Users;
import com.example.testapplication.repository.UsersRepo;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RESTfulController {

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss Z", timezone = "America/Los_Angeles")

    @Autowired
    private UsersRepo usersRepo;

    @Value("${file.upload-dir}")
    private String uploadPath;

  /*  public String main(Map <String, Object> model) {
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
    /*@PostMapping("deleteAll")
    public String deleteAll(Map<String, Object> map){
        Iterable<Users> users = usersRepo.findAll();
        usersRepo.deleteAll(users);
        map.put("users", users);
        return "redirect:/";
    }*/

   /* @PostMapping("findById")
    public String findById(Integer id, Map<String, Object> map){
        return String.valueOf(usersRepo.findById(id));
    }
*/
    @GetMapping("findById")
    List<Users> users(Integer id){
       List<Users> users = usersRepo.findAllById(id);
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users not found");
        }
        return users;
    }

    @GetMapping("findByStatus")
    HashMap<String, Object> users(String status){
        List <Users> user = usersRepo.findByStatus(status);
        HashMap<String, Object> result = new HashMap<>();
        for (Users t : user) {
            result.put("id", t.getId());
            result.put("status", t.getStatus());
            result.put("avatar", t.getAvatar());
            result.put("query_timestamp", new Date());
        }
        return result;
    }

    @PostMapping("/registration")
    public Map<String, Object> addNewUser(@RequestParam("file") MultipartFile file, Users user) throws IOException {
        //Если файл выбран, запускается метод срхранения на HDD на сервере
        if (file != null && !file.isEmpty()) {
            user.setAvatar(SavingOnHDD.save(uploadPath, file));
        }
        HashMap<String, Object> resultId = new HashMap<>();
        user.setActive(true);
        user.setReg_date(new Date());
        usersRepo.save(user);
        resultId.put("id", user.getId());
        return resultId;
    }

    @PostMapping("changeStatus")
    public HashMap<String, Object> changeStatus(@RequestParam Integer id,
                                 String status) {
        HashMap<String, Object> statuses = new HashMap<>();
        Users user = usersRepo.findById(id);
        String prevStatus = user.getStatus();
        user.setStatus(status);
        statuses.put("id", user.getId());
        statuses.put("status", status);
        statuses.put("prevStatus", prevStatus);
        usersRepo.save(user);

        return statuses;
    }

}


