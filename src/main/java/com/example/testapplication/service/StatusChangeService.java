package com.example.testapplication.service;

import com.example.testapplication.entity.Users;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;

public class StatusChangeService {
    // метод создания вывода при изменении статуса
    public HashMap<String, Object> changeToJson(Users user, String prevStatus){
        HashMap<String, Object> statuses = new HashMap<>();
        statuses.put("id", user.getId());
        statuses.put("status", user.getStatus());
        statuses.put("prevStatus", prevStatus);
        statuses.put("editTimestamp", Timestamp.valueOf(LocalDateTime.now()));
        return statuses;
    }
}
