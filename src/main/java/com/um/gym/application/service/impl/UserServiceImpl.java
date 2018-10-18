package com.um.gym.application.service.impl;

import com.um.gym.application.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<User, Long> {
    @Override
    public User create(User entity) {
        return super.create(entity);
    }

    @Override
    public void delete(User entity) {
        super.delete(entity);
    }

    @Override
    public User update(User entity) {
        return super.update(entity);
    }

    @Override
    public User findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public List<User> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }
}
