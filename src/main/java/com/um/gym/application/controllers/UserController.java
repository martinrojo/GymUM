package com.um.gym.application.controllers;

import com.um.gym.application.models.User;
import com.um.gym.application.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.hibernate.*;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userRepository.findAll()/* session.createCriteria(com.springapp.mvc.User.class).list()*/);
        return "users";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, BindingResult result) {

        userRepository.save(user);

        return "redirect:/";
    }

    @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {

        userRepository.delete(userRepository.getOne(userId));

        return "redirect:/";
    }


    @GetMapping("/api/users")
    public ResponseEntity listUsersJson(ModelMap model) throws JSONException {
        try {
            JSONArray userArray = new JSONArray();
            if (userRepository.findAll().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no hay datos");
            } else {
                return ResponseEntity.ok().body(userRepository.findAll());
            /*for (User user : userRepository.findAll()) {

                JSONObject userJSON = new JSONObject();
                userJSON.put("id", user.getId());
                userJSON.put("nombre", user.getNombre());
                userJSON.put("apellido", user.getApellido());
                userJSON.put("dni", user.getDni());
                userJSON.put("email", user.getEmail());
                userArray.put(userJSON);
                return ResponseEntity.ok().body(userArray.toString());*/
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}