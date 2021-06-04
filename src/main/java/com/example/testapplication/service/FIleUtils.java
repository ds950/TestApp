package com.example.testapplication.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class FIleUtils {

    public static String saveToCloud(MultipartFile file) {
        //создание уникального имени файла
        String resultFileName = UUID.randomUUID() + "." + file.getOriginalFilename();
        //переименование для добавления в базу
        String fileOS = "/" + resultFileName;
        //имитация загрузки файла на сервер с ожиданием
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return fileOS;
    }



}
