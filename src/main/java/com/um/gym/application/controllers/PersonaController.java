package com.um.gym.application.controllers;

import com.um.gym.application.models.Movimiento;
import com.um.gym.application.models.Persona;
import com.um.gym.application.service.impl.PersonaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class PersonaController {

    @Autowired
    private PersonaServiceImpl personaService;

    private static final Logger logger = LoggerFactory.getLogger(PersonaController.class);

    @GetMapping("/personas")
    public List<Persona> listUsers(String nombre, String dni) {
        return personaService.findAll(nombre, dni);
    }

    @GetMapping("/personas/{idUser}")
    public Persona findUser(@PathVariable("idUser") Long idUser) {
        return personaService.findById(idUser);
    }

    @GetMapping("/personas/{idUser}/movimientos")
    public List<Movimiento> getMovements(@PathVariable("idUser") Long idUser) {
        return personaService.findById(idUser).getMovimientos();
    }

    @PostMapping("/personas")
    public ResponseEntity create(@RequestBody Persona user) {
        return personaService.createPersona(user);
    }

    @PutMapping("/personas")
    public ResponseEntity updatePersona(@RequestBody Persona user) {
        return personaService.updatePersona(user);
    }

    @DeleteMapping("/personas/{idUser}")
    public ResponseEntity deleteUser(@PathVariable("idUser") Long id) {
        logger.info("DELETE | Persona eliminada.");
        personaService.deleteById(id);
        return ResponseEntity.ok("Deleted User with id = " + id);
    }

}