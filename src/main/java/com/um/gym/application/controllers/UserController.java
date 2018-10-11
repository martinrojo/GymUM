package com.um.gym.application.controllers;

import com.um.gym.application.models.User;
import com.um.gym.application.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

	@RequestMapping(value = "/api/users", method = RequestMethod.GET)
	public
	@ResponseBody
	String listUsersJson(ModelMap model) throws JSONException {
		JSONArray userArray = new JSONArray();
		for (User user : userRepository.findAll()) {
			JSONObject userJSON = new JSONObject();
			userJSON.put("id", user.getId());
			userJSON.put("firstName", user.getFirstName());
			userJSON.put("lastName", user.getLastName());
			userJSON.put("dni", user.getDni());
			userJSON.put("email", user.getEmail());
			userArray.put(userJSON);
		}
		return userArray.toString();
	}
}