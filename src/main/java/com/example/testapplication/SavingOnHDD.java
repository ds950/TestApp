package com.example.testapplication;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class SavingOnHDD {
    public static String save(String uploadPath, MultipartFile file) throws IOException {
        File uploadDir = new File(uploadPath);
        //Если директория не существует - приложение создает новую
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        //Шифрование имени
        String resaultFileName = UUID.randomUUID() + "." + file.getOriginalFilename();
        //Переменная хранит абсолютный путь и имя файла
        String fileOS = uploadPath + "/" + resaultFileName;
        //Сохранение файла на сервере
        file.transferTo(new File(fileOS));

        return resaultFileName;
    }
}
