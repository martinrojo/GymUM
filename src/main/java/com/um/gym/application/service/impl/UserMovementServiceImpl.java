package com.um.gym.application.service.impl;

import com.um.gym.application.models.UserMovement;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMovementServiceImpl extends ServiceImpl<UserMovement, Long> {
    @Override
    public UserMovement create(UserMovement entity) {
        return super.create(entity);
    }

    @Override
    public void delete(UserMovement entity) {
        super.delete(entity);
    }

    @Override
    public UserMovement update(UserMovement entity) {
        return super.update(entity);
    }

    @Override
    public UserMovement findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public List<UserMovement> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }
}
