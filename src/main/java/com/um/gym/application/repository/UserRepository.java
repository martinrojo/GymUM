package com.um.gym.application.repository;


import com.um.gym.application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}