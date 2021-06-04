package com.example.testapplication.service;

import com.example.testapplication.entity.Users;
import com.example.testapplication.repository.AvatarRepo;
import com.example.testapplication.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private AvatarRepo avatarRepo;

    //добавляем в базу uri аватарки,
    public Users addNewUser (Users user, String filename){
        //если файл присутствует в базе
        if(!avatarRepo.findByFileUri(filename).toString().isEmpty()){
            user.setAvatar(filename);
        } else {
            //вывод ошибки
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image with URI: " + filename + " not found");
        }
        //таймстэмп создания объекта
        user.setReg_date(Timestamp.valueOf(LocalDateTime.now()));
        usersRepo.save(user);
        return user;
    }



}
