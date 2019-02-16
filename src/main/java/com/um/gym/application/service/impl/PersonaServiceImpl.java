package com.um.gym.application.service.impl;

import com.um.gym.application.ExceptionHandler.MyResourceNotFoundException;
import com.um.gym.application.models.Persona;
import com.um.gym.application.repository.PersonaRepository;
import org.omg.SendingContext.RunTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl extends ServiceImpl<Persona, Long> {

    private static final Logger logger = LoggerFactory.getLogger(PersonaServiceImpl.class);

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public Persona create(Persona entity) throws RuntimeException {
        try {
            if (!(entity.getDni().length() == 8)){
                throw new MyResourceNotFoundException("DNI must contains 8 numeric digits");
            }
            try {
                Integer.parseInt(entity.getDni());
            } catch (NumberFormatException nfe){
                throw new MyResourceNotFoundException("DNI must be numeric " + nfe.getMessage());
            }
            if (personaRepository.findByEmail(entity.getEmail()) != null){
                throw new MyResourceNotFoundException("Email is already register");
            }
            if (personaRepository.findByDni(entity.getDni()) != null){
                throw new MyResourceNotFoundException("DNI is already register");
            } else {
                entity.setHoras(0);
                entity.setMinutos(0);
                return super.create(entity);
            }
        }  catch (Exception e) {
            throw new MyResourceNotFoundException("User not created, error: " + e.getMessage());
        }
    }

    @Override
    public void delete(Persona entity) {
        super.delete(entity);
    }

    @Override
    public Persona update(Persona entity) throws RuntimeException {
        try {
            if (!(entity.getDni().length() == 8)){
                throw new MyResourceNotFoundException("DNI must contains 8 numeric digits");
            }
            try {
                Integer.parseInt(entity.getDni());
            } catch (NumberFormatException nfe){
                throw new MyResourceNotFoundException("DNI must be numeric " + nfe.getMessage());
            }
            if (personaRepository.findByEmail(entity.getEmail()).getId() != entity.getId()){
                throw new MyResourceNotFoundException("Email is already register");
            }
            if (personaRepository.findByDni(entity.getDni()).getId() != entity.getId()){
                throw new MyResourceNotFoundException("DNI is already register");
            }
            if (personaRepository.findById(entity.getId()) == null) {
                logger.warn("PUT | Persona no existente.");
                throw new MyResourceNotFoundException("Persona not found");
            } else {
                logger.info("PUT | Persona actualizado: " + personaRepository.findById(entity.getId()));
                return super.update(entity);
            }
        } catch (Exception e) {
            throw new MyResourceNotFoundException("Persona not updated, error: " + e.getMessage());
        }
    }

    @Override
    public Persona findById(Long aLong) throws RuntimeException {
        if (!(personaRepository.findById(aLong).isPresent())){
            throw new MyResourceNotFoundException("User not found with id:"+aLong);
        }
        return super.findById(aLong);
    }

    @Override
    public List<Persona> findAll() {
        return super.findAll();
    }

    public List<Persona> findAll(String nombre, String dni) {
        try {
            if (dni != null) {
                if (personaRepository.findAllByDni(dni).isEmpty()) {
                    throw new MyResourceNotFoundException("Not User available with dni: " + dni);
                } else {
                    logger.info("GET | " + personaRepository.findAllByDni(dni));
                    return personaRepository.findAllByDni(dni);
                }
            }

            if (nombre != null) {
                if (personaRepository.findAllByNombreAndApellido(nombre).isEmpty()) {
                    logger.warn("GET | No hay personas con nombre " + nombre);
                    throw new MyResourceNotFoundException("Not User available with name: " + nombre);
                } else {
                    logger.info("GET | " + personaRepository.findAllByNombreAndApellido(nombre));
                    return personaRepository.findAllByNombreAndApellido(nombre);
                }
            }

            if (personaRepository.findAll().isEmpty()) {
                throw new MyResourceNotFoundException("No content available");
            } else {
                return super.findAll();
            }
        } catch (Exception e) {
            throw new MyResourceNotFoundException(" Without search results: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long aLong) {
        if (!(personaRepository.findById(aLong).isPresent())){
            throw new MyResourceNotFoundException("User not found with id:"+aLong);
        }
        super.deleteById(aLong);
    }

    public List<Persona> findAllByDni(String dni){
        return personaRepository.findAllByDni(dni);
    }

    public List<Persona> findAllByNombreAndApellido(String nombre) {
        return personaRepository.findAllByNombreAndApellido(nombre);
    }

    public Persona findByDni(String dni) throws RuntimeException {
        if ((personaRepository.findByDni(dni)) == null){
            throw new MyResourceNotFoundException("User not found with dni:"+dni);
        }
        return personaRepository.findByDni(dni);
    }

    public Persona findByEmail(String email) throws RuntimeException {
        if ((personaRepository.findByEmail(email)) == null){
            throw new MyResourceNotFoundException("User not found with email:"+email);
        }
        return personaRepository.findByEmail(email);
    }
}
