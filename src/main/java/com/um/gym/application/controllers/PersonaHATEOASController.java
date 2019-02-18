package com.um.gym.application.controllers;

import com.um.gym.application.models.Persona;
import com.um.gym.application.service.impl.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@ExposesResourceFor(Persona.class)
@RestController
@RequestMapping("/hateoas/")
public class PersonaHATEOASController {

    @Autowired
    private PersonaServiceImpl personaService;

    @Autowired
    EntityLinks entityLinks;

    @GetMapping("/personas")
    public Resources<Resource<Persona>> getPersonas() {
        List<Persona> personaLista = personaService.findAll();
        List<Resource<Persona>> personaResources =
                personaLista.stream()
                        .map(PersonaHATEOASController::createPersonaResource)
                        .collect(Collectors.toList());
        Link selfRel = linkTo(methodOn(PersonaHATEOASController.class)
                .getPersonas()).withSelfRel();
        return new Resources<>(personaResources, selfRel);
    }

    @GetMapping("/personas/{idUser}")
    public Resource<Persona> findUser(@PathVariable("idUser") Long idUser) {
        Link selfLink = linkTo(methodOn(PersonaHATEOASController.class)
                .findUser(idUser)).withSelfRel();
        return new Resource<>(personaService.findById(idUser), selfLink, createMovementsLink(idUser));
    }

    private static Resource<Persona> createPersonaResource(Persona e) {
        Link personaLink = linkTo(methodOn(PersonaHATEOASController.class)
                .findUser(e.getId())).withSelfRel();
        return new Resource<>(e, personaLink);
    }

    private static Link createMovementsLink(long idUser) {
        return linkTo(methodOn(MovimientoController.class)
                .findByPersonaId(idUser)).withRel("movements");
    }
}
