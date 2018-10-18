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

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        model.addAttribute("user", new Usuario());
        model.addAttribute("users", userServiceImpl.findAll()/* session.createCriteria(com.springapp.mvc.Usuario.class).list()*/);
        return "users";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") Usuario usuario, BindingResult result) {

        userServiceImpl.create(usuario);

        return "redirect:/";
    }

    @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {

        userServiceImpl.delete(userServiceImpl.findById(userId));

        return "redirect:/";
    }


    @GetMapping("/api/users")
    public ResponseEntity listUsersJson(ModelMap model) throws JSONException {
        try {
            JSONArray userArray = new JSONArray();
            if (userServiceImpl.findAll().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no hay datos");
            } else {
                return ResponseEntity.ok().body(userServiceImpl.findAll());
            /*for (Usuario usuario : userRepository.findAll()) {

                JSONObject userJSON = new JSONObject();
                userJSON.put("id", usuario.getId());
                userJSON.put("nombre", usuario.getNombre());
                userJSON.put("apellido", usuario.getApellido());
                userJSON.put("dni", usuario.getDni());
                userJSON.put("email", usuario.getEmail());
                userArray.put(userJSON);
                return ResponseEntity.ok().body(userArray.toString());*/
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}