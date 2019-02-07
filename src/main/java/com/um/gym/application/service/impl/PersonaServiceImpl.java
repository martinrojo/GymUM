package com.um.gym.application.service.impl;

import com.um.gym.application.models.Persona;
import com.um.gym.application.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl extends ServiceImpl<Persona, Long> {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Persona create(Persona entity) {
        entity.setHoras(0);
        entity.setMinutos(0);
        return super.create(entity);
    }

    @Override
    public void delete(Persona entity) {
        super.delete(entity);
    }

    @Override
    public Persona update(Persona entity) {
        return super.update(entity);
    }

    @Override
    public Persona findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public List<Persona> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    public List<Persona> findAllByDni(String dni){
        return personaRepository.findAllByDni(dni);
    }

    public List<Persona> findAllByNombreAndApellido(String nombre) {
        return personaRepository.findAllByNombreAndApellido(nombre);
    }

    public Persona findByDni(String dni){
        return personaRepository.findByDni(dni);
    }
}
