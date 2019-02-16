package com.um.gym.application.controllers;

import com.um.gym.application.models.Movimiento;
import com.um.gym.application.models.Persona;
import com.um.gym.application.service.impl.PersonaServiceImpl;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
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
    public List<Movimiento> getMovements(@PathVariable("idUser") Long idUser){
        return personaService.findById(idUser).getMovimientos();
    }

    @PostMapping("/personas")
    public Persona create(@RequestBody Persona user) {
        return personaService.create(user);
    }

    @PutMapping("/personas")
    public Persona update(@RequestBody Persona user) {
        return personaService.update(user);
    }

    @DeleteMapping("/personas/{idUser}")
    public ResponseEntity deleteUser(@PathVariable("idUser") Long id) {
        logger.info("DELETE | Persona eliminada.");
        personaService.deleteById(id);
        return ResponseEntity.ok("Deleted User with id = " + id);
    }
}