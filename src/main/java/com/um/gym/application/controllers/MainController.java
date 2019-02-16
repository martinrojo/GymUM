package com.um.gym.application.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MainController {

    @RequestMapping(method = RequestMethod.OPTIONS)
    public HttpServletResponse handle(HttpServletResponse theHttpServletResponse) throws IOException {
        theHttpServletResponse.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
        theHttpServletResponse.addHeader("Access-Control-Max-Age", "60");
        theHttpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        theHttpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        return theHttpServletResponse;
    }
}
*/