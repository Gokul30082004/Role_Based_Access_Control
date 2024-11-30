package com.example.projectrbac.repository;

import com.example.projectrbac.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);
}
