package com.um.gym.application.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MainController {

    /*@RequestMapping(method = RequestMethod.OPTIONS)
    public HttpServletResponse handle(HttpServletResponse theHttpServletResponse) throws IOException {
        theHttpServletResponse.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
        theHttpServletResponse.addHeader("Access-Control-Max-Age", "60");
        theHttpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        theHttpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        return theHttpServletResponse;
    }*/

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity options(HttpServletResponse response) {
        logger.info("OPTIONS /api called");
        response.setHeader("Allow", "HEAD,GET,PUT,DELETE,POST,OPTIONS");
        final String Options = "PersonaController:  \n\tGET method (/personas) returns all users \n" +
                "\tGET method (/personas/{idUser}) returns a specific User with ID = idUser \n" +
                "\tGET method (/personas/{idUser}/movimientos) returns all movements from a specific person with ID = idUser \n" +
                "\tPOST method (/personas/Persona atribute) create a Persona \n" +
                "\tPUT method (/personas/Persona) update a existing Persona \n" +
                "\tDELETE method (/personas/{idUser}) delete a user with ID = idUser" +
                "\nMovimientoController:  \n\tGET method (/movimientos) returns all movimientos \n" +
                "\tGET method (/movimientos/{id}) returns a specific Movimiento with ID = id \n" +
                "\tGET method (/movimientos/personas/{id}) returns all movements from a specific person with ID = id \n" +
                "\tPOST method (/movimientos) create a Movimiento \n" +
                "\tPUT method (/movimientos/{id}) update a existing Movimiento \n" +
                "\tDELETE method (/movimientos/{id}) delete a user with ID = id";
        return ResponseEntity.status(HttpStatus.OK).body(Options);
    }
}
