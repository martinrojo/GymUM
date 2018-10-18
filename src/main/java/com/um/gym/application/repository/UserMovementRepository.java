package com.um.gym.application.repository;


import com.um.gym.application.models.UserMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMovementRepository extends JpaRepository<UserMovement, Long> {
}