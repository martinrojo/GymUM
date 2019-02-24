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
    public List<Movimiento> findAll() {
        return movimientoServiceImpl.findAll();
    }

    @GetMapping("/movimientos/{id}")
    public Movimiento findById(@PathVariable Long id) {
        return movimientoServiceImpl.findById(id);
    }

    @GetMapping("/movimientos/personas/{id}")
    public Movimiento findByPersonaId(@PathVariable Long id) {
        return movimientoServiceImpl.findByIdPersona(id);
    }

    @PostMapping("/movimientos")
    public Movimiento create(@RequestBody Movimiento movimiento) {
        return movimientoServiceImpl.create(movimiento);
    }

    @PutMapping("/movimientos/{id}")
    public Movimiento edit(@PathVariable("id") Long id) {
        return movimientoServiceImpl.update(id);
    }


    @DeleteMapping("/movimientos/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return movimientoServiceImpl.delete(id);
    }
}
