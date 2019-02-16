package com.um.gym.application.controllers;

import com.um.gym.application.models.Movimiento;
import com.um.gym.application.service.impl.MovimientoServiceImpl;
import com.um.gym.application.service.impl.PersonaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovimientoController {

    @Autowired
    private MovimientoServiceImpl movimientoServiceImpl;

    @Autowired
    private PersonaServiceImpl personaServiceImpl;

    private static final Logger logger = LoggerFactory.getLogger(MovimientoController.class);

    @GetMapping("/movimientos")
    public ResponseEntity findAll() {
        try {
            if (movimientoServiceImpl.findAll().isEmpty()) {
                logger.warn("GET | No hay movimientos.");
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(false);
            } else {
                List<Movimiento> lista = movimientoServiceImpl.findAll();
                logger.info("GET | " + lista.toString());
                return ResponseEntity.status(HttpStatus.OK).body(lista);
            }
        } catch (Exception e) {
            logger.error("GET | ERROR:" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @GetMapping("/movimientos/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        if (movimientoServiceImpl.findByUsuario(id).isEmpty()) {
            logger.warn("GET | No hay movimientos.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay movimientos.");
        }
        try {
            List<Movimiento> lista = movimientoServiceImpl.findByUsuario(id);
            logger.info("GET | " + lista.toString());
            return ResponseEntity.status(HttpStatus.OK).body(lista);
        } catch (Exception e) {
            logger.error("GET | ERROR: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @GetMapping("/movimientos/personas/{id}")
    public ResponseEntity findByPersonaId(@PathVariable Long id) {
        if (movimientoServiceImpl.findByUsuario(id).isEmpty()) {
            logger.warn("GET | No hay movimientos.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay movimientos.");
        }
        try {
            List<Movimiento> movimientos = personaServiceImpl.findById(id).getMovimientos();
            for (Movimiento m : movimientos) {
                if (m.getFechaSalida() == null) {
                    logger.info("GET | " + m.toString());
                    return ResponseEntity.status(HttpStatus.OK).body(m);
                }
            }
            logger.warn("GET | No hay movimiento creado.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(false);
        } catch (Exception e) {
            logger.error("GET | ERROR: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @PostMapping("/movimientos")
    public ResponseEntity create(@RequestBody Movimiento movimiento, BindingResult result) {
        List<Movimiento> movimientos = movimientoServiceImpl.findByUsuario(movimiento.getPersona().getId());
        if (movimientos != null) {
            for (Movimiento m : movimientos) {
                if (m.getFechaSalida() == null) {
                    logger.warn("POST | Hay un movimiento sin terminar para el usuario " + movimiento.getPersona().getId());
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
                }
            }
        }
        try {
            movimientoServiceImpl.create(movimiento);
            logger.info("POST | Movimiento creado.");
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } catch (Exception e) {
            logger.error("POST | ERROR: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @PutMapping("/movimientos/{id}")
    public ResponseEntity edit(@RequestBody Movimiento movimiento) {
        try {
            movimientoServiceImpl.update(movimiento);
            logger.info("PUT | Movimiento actualizado.");
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } catch (Exception e) {
            logger.error("PUT | ERROR:" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }


    @DeleteMapping("/movimientos/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            movimientoServiceImpl.deleteById(id);
            logger.info("DELETE | Movimiento eliminado.");
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (Exception e) {
            logger.error("DELETE | ERROR:" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
}
