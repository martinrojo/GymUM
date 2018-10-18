package com.um.gym.application.service.impl;

import com.um.gym.application.models.Movimiento;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoServiceImpl extends ServiceImpl<Movimiento, Long> {
    @Override
    public Movimiento create(Movimiento entity) {
        return super.create(entity);
    }

    @Override
    public void delete(Movimiento entity) {
        super.delete(entity);
    }

    @Override
    public Movimiento update(Movimiento entity) {
        return super.update(entity);
    }

    @Override
    public Movimiento findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public List<Movimiento> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }
}
