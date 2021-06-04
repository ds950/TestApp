package com.example.testapplication.controller;

import com.example.testapplication.service.FIleUtils;
import com.example.testapplication.entity.Avatar;
import com.example.testapplication.entity.Users;
import com.example.testapplication.repository.AvatarRepo;
import com.example.testapplication.repository.UsersRepo;
import com.example.testapplication.service.StatisticResult;
import com.example.testapplication.service.StatusChangeService;
import com.example.testapplication.service.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
public class RESTfulController {

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss Z", timezone = "America/Los_Angeles")

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private AvatarRepo avatarRepo;

    @PostMapping("registration")
    public Integer addNewUser(String fIleUtils, Users user) {
        //Если файл выбран, запускается метод срхранения на HDD на сервере
        Users users = new UserService().addNewUser(user, fIleUtils);
        return users.getId();
    }

    //поиск по Id  и вывод всех данных
    @GetMapping("findById")
    List<Users> users(Integer id){
       List<Users> users = usersRepo.findAllById(id);
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Users not found");
        }
        return users;
    }

    //поиск по статусу и времени. если время не указано, ищет только по статусу
    @GetMapping("stats")
    HashMap<String, Object> findByChangeTime(String status, Timestamp time , StatisticResult statisticResult) {
        List<Users> users;
        if (time != null) {
            users = usersRepo.findAllByStatusChangeTimestampAfter(time);
        } else {
            users = usersRepo.findByStatus(status);
        }
        return statisticResult.buildStats(users);
    }

    //добавление уникального имени картинки в базу, имитирует запись файла в бд
    @PostMapping("addAvatar")
    public String loadAvatar(@RequestParam("file") MultipartFile file, Avatar avatar) {
        String result = FIleUtils.saveToCloud(file);
        avatar.setFileUri(result);
        avatarRepo.save(avatar);
        return result;
    }

    //смена статуса с последующим отображением предыдущего статуса и времени изменения
    @PostMapping("changeStatus")
    public HashMap<String, Object> changeStatus(@RequestParam Integer id, String status, StatusChangeService statusChangeService) {
        Users user = usersRepo.findById(id);
        Integer userId = user.getId();
        Users updateUser = usersRepo.findById(userId);
        String prevStatus = updateUser.getStatus();
        updateUser.setStatus(status);
        updateUser.setStatusChangeTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        usersRepo.save(updateUser);

        return statusChangeService.changeToJson(user,prevStatus);
    }

}


