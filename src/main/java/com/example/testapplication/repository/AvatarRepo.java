package com.example.testapplication.repository;

import com.example.testapplication.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepo extends JpaRepository<Avatar, Long> {
    Avatar findByFileUri(String fileUri);
}
