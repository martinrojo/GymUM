package com.um.gym.application.controllers;

import com.um.gym.application.models.Persona;
import com.um.gym.application.service.impl.PersonaServiceImpl;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;


@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    @Autowired
    private PersonaServiceImpl personaService;

    private static final Logger logger = LoggerFactory.getLogger(PersonaController.class);

    @GetMapping
    public ResponseEntity listUsers(String nombre, String dni) throws JSONException {
        try {
            if (dni != null) {
                if (personaService.findAllByDni(dni).isEmpty()) {
                    logger.warn("GET | No hay usuarios con el DNI: " + dni);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(false);
                } else {
                    List<Persona> listaU = personaService.findAllByDni(dni);
                    logger.info("GET | " + listaU.toString());
                    return ResponseEntity.status(HttpStatus.OK).body(listaU);
                }
            }

            if (nombre != null) {
                if (personaService.findAllByNombreAndApellido(nombre).isEmpty()) {
                    logger.warn("GET | No hay personas con nombre " + nombre);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(false);
                } else {
                    List<Persona> listaU = personaService.findAllByNombreAndApellido(nombre);
                    logger.info("GET | " + listaU.toString());
                    return ResponseEntity.status(HttpStatus.OK).body(listaU);
                }
            }

            if (personaService.findAll().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no hay datos");
            } else {
                List<Persona> listaU = personaService.findAll();
                return ResponseEntity.status(HttpStatus.OK).body(listaU);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{idUser}")
    public ResponseEntity findUser(@PathVariable("idUser") Long idUser) throws JSONException {
        try {
            if (personaService.findById(idUser).getEmail() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Persona no encontrado");
            } else {
                Persona persona = personaService.findById(idUser);
                return ResponseEntity.status(HttpStatus.OK).body(persona);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{idUser}/movimientos")
    public ResponseEntity insertUser(@PathVariable("idUser") Long idUser) {
        try {
            return ResponseEntity.ok().body(personaService.findById(idUser).getMovimientos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Persona user) {
        try {
            if (personaService.findByDni(user.getDni()) == null) {
                personaService.create(user);
                logger.info("POST | Persona creado: " + personaService.findById(user.getId()));
                return ResponseEntity.status(HttpStatus.CREATED).body(true);
            } else {
                logger.warn("POST | Error. El DNI ingresado ya existe." );
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
            }
        } catch (Exception e) {
            logger.error("POST | ERROR: " + e.getMessage() );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Persona user) throws JSONException {
        try {
            if (personaService.findById(user.getId()) != null) {
                personaService.update(user);
                logger.info("PUT | Persona actualizado: " + personaService.findById(user.getId()));
                return ResponseEntity.ok().body(true);
            } else {
                logger.warn("PUT | Persona no existente.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
        } catch (Exception e) {
            logger.error("PUT | ERROR: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity deleteUser(@PathVariable("idUser") Long id) {
        try {
            personaService.deleteById(id);
            logger.info("DELETE | Persona eliminada." );
            return ResponseEntity.ok().body(true);
        } catch (Exception e) {
            logger.error("DELETE | ERROR: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
}