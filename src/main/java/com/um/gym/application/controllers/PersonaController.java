package com.um.gym.application.controllers;

import com.um.gym.application.models.Persona;
import com.um.gym.application.service.impl.PersonaServiceImpl;
import com.um.gym.application.utils.JsonUsuarioController;
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

    @Autowired
    private JsonUsuarioController jsonUsuarioController;


    private static final Logger logger = LoggerFactory.getLogger(PersonaController.class);

    /*@RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        model.addAttribute("user", new Persona());
        model.addAttribute("users", personaService.findAll() /* session.createCriteria(com.springapp.mvc.User.class).list());
        return "users";
    }*/

    /*@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") Persona user, BindingResult result) {

        personaService.create(user);

        return "redirect:/";
    }*/

    @GetMapping
    public ResponseEntity listUsers(String nombre, String dni) throws JSONException {
        try {
            if (dni != null) {
                if (personaService.findAllByDni(dni).isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no hay usuarios con el DNI:" + dni);
                } else {
                    List<Persona> listaU = personaService.findAllByDni(dni);
                    String json = jsonUsuarioController.getList(listaU);
                    return ResponseEntity.status(HttpStatus.OK).body(json);
                }
            }

            if (nombre != null) {
                if (personaService.findAllByNombreAndApellido(nombre).isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no hay usuarios con el Nombre o Apellido:" + nombre);
                } else {
                    List<Persona> listaU = personaService.findAllByNombreAndApellido(nombre);
                    String json = jsonUsuarioController.getList(listaU);
                    return ResponseEntity.status(HttpStatus.OK).body(json);
                }
            }

            if (personaService.findAll().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no hay datos");
            } else {
                List<Persona> listaU = personaService.findAll();
                String json = jsonUsuarioController.getList(listaU);
                return ResponseEntity.status(HttpStatus.OK).body(json);
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
                String json = jsonUsuarioController.getObj(persona);
                return ResponseEntity.status(HttpStatus.OK).body(json);
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
                return ResponseEntity.ok().body(personaService.create(user));
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(personaService.create(user));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity insertUser(@RequestBody Persona user) throws JSONException {
        try {
            if (personaService.findById(user.getId()) != null) {
                return ResponseEntity.ok().body(personaService.update(user));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no existente.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity deleteUser(@PathVariable("idUser") Long idUser) {
        try {
            personaService.deleteById(idUser);
            return ResponseEntity.ok().body("Persona borrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}