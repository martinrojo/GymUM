package com.um.gym.application.controllers;

import com.um.gym.application.models.Movimiento;
import com.um.gym.application.service.impl.MovimientoServiceImpl;
import com.um.gym.application.utils.JsonMovimientoController;
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
    private JsonMovimientoController jsonMovimientoController;

    @GetMapping("/movimientos")
    public ResponseEntity findAll() {
        try {
            if (movimientoServiceImpl.findAll().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay movimientos.");
            } else {
                List<Movimiento> lista = movimientoServiceImpl.findAll();
                String json = jsonMovimientoController.getList(lista);
                return ResponseEntity.status(HttpStatus.OK).body(json);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/movimientos/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        if (movimientoServiceImpl.findByUsuario(id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay movimientos.");
        }
        try {
            List<Movimiento> lista = movimientoServiceImpl.findByUsuario(id);
            String json = jsonMovimientoController.getList(lista);
            return ResponseEntity.status(HttpStatus.OK).body(json);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/movimientos/")
    public ResponseEntity create(@RequestBody Movimiento movimiento, BindingResult result) {
        List<Movimiento> movimientos = movimientoServiceImpl.findByUsuario(movimiento.getPersona().getId());
        if (movimientos != null) {
            for (Movimiento m : movimientos) {
                if (m.getFechaSalida() == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hay un movimiento sin terminar para el usuario " + movimiento.getPersona().getId());
                }
            }
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(movimientoServiceImpl.create(movimiento));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/movimientos/{id}")
    public ResponseEntity edit(@RequestBody Movimiento movimiento){
        return ResponseEntity.status(HttpStatus.OK).body(movimientoServiceImpl.update(movimiento));
    }


    @DeleteMapping("/movimientos/{id}")
    public ResponseEntity delete(@PathVariable Long id){
       try {
            movimientoServiceImpl.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se eliminó el movimiento de ID: " + id);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
