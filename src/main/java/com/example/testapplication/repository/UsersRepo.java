package com.example.testapplication.repository;

import com.example.testapplication.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface UsersRepo extends JpaRepository<Users, Long> {
    Users findById(Integer id);
    List <Users> findAllById(Integer id);
    List<Users> findByStatus(String status);
    List<Users> findAllByStatusChangeTimestampAfter(Timestamp time);
}
