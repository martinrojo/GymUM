package com.um.gym.application.service.impl;

import com.um.gym.application.models.Movimiento;
import com.um.gym.application.models.Usuario;
import com.um.gym.application.repository.MovimientoRepository;
import com.um.gym.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoServiceImpl extends ServiceImpl<Movimiento, Long> {

    @Autowired
    private MovimientoRepository dao;

    @Autowired
    private UserServiceImpl userService;

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

    public List<Movimiento> findByUsuario(Long id){
        return dao.findAllByUsuario(userService.findById(id));
    }
}
