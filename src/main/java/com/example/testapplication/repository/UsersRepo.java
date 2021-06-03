package com.example.testapplication.repository;

import com.example.testapplication.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepo extends JpaRepository<Users, Long> {

    Users findById(Integer id);
    Users findByUsername(String username);
    List<Users> findByStatus(String status);
}
