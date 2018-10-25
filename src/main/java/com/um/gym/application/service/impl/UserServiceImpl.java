package com.um.gym.application.service.impl;

import com.um.gym.application.models.Usuario;
import com.um.gym.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<Usuario, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Usuario create(Usuario entity) {
        return super.create(entity);
    }

    @Override
    public void delete(Usuario entity) {
        super.delete(entity);
    }

    @Override
    public Usuario update(Usuario entity) {
        return super.update(entity);
    }

    @Override
    public Usuario findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public List<Usuario> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    public List<Usuario> findAllByDni(String dni){
        return userRepository.findAllByDni(dni);
    }
    public List<Usuario> findAllByNombre(String nombre){
        return userRepository.findAllByNombre(nombre);
    }
    public List<Usuario> findAllByApellido(String apellido) { return userRepository.findAllByApellido(apellido);}
}
