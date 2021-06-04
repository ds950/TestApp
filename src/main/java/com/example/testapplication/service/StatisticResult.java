package com.example.testapplication.service;

import com.example.testapplication.entity.Users;
import com.example.testapplication.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


public class StatisticResult {

    @Autowired
    UsersRepo usersRepo;
    // метод создания вывода статистики
    public HashMap<String, Object> buildStats(List<Users> users){
        HashMap<String, Object> result = new HashMap<>();
        //записываем каждый элемент базы в хешмэп с уникальным таймстэмпом
        for (Users u : users) {
            result.put("id", u.getId());
            result.put("username", u.getUsername());
            result.put("imageURI", u.getAvatar());
            result.put("timestamp", u.getStatusChangeTimestamp());
    }
    return result;
    }
}
