package com.um.gym.application.controllers;

import com.um.gym.application.models.Usuario;
import com.um.gym.application.service.impl.UserServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        model.addAttribute("user", new Usuario());
        model.addAttribute("users", userServiceImpl.findAll()/* session.createCriteria(com.springapp.mvc.User.class).list()*/);
        return "users";
    }

    /*@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") Usuario user, BindingResult result) {

        userServiceImpl.create(user);

        return "redirect:/";
    }*/

    @DeleteMapping("/usuarios/{idUser}")
    public ResponseEntity deleteUser(@PathVariable("idUser") Long idUser) {
        try {
            userServiceImpl.deleteById(idUser);
            return ResponseEntity.ok().body("Usuario borrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity listUsersJson(String nombre, String apellido, String dni) throws JSONException {
        try {
            JSONArray userArray = new JSONArray();
            if (userServiceImpl.findAll().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no hay datos");
            } else {
                return ResponseEntity.ok().body(userServiceImpl.findAll());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/usuarios/{idUser}")
    public ResponseEntity findUser(@PathVariable("idUser") Long idUser) throws JSONException {
        try {
            if (userServiceImpl.findById(idUser).getEmail() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario no encontrado");
            } else {
                return ResponseEntity.ok().body(userServiceImpl.findById(idUser));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/usuarios/")
    public ResponseEntity insertUser(@RequestBody Usuario user) throws JSONException {
        try {
            if(user.getId()!=0){
                return ResponseEntity.ok().body(userServiceImpl.update(user));
            }
            else {
                return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImpl.create(user));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/usuarios/{idUser}/movimientos")
    public ResponseEntity insertUser(@PathVariable("idUser") Long idUser) {
        try {
            return ResponseEntity.ok().body(userServiceImpl.findById(idUser).getMovimientos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }
}