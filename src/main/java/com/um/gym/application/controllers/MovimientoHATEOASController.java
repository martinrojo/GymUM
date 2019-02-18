package com.um.gym.application.controllers;

import com.um.gym.application.models.Movimiento;
import com.um.gym.application.service.impl.MovimientoServiceImpl;
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

@ExposesResourceFor(Movimiento.class)
@RestController
@RequestMapping("/hateoas/")
public class MovimientoHATEOASController {

    @Autowired
    private MovimientoServiceImpl movimientoService;

    @Autowired
    private PersonaServiceImpl personaService;

    @Autowired
    EntityLinks entityLinks;

    @GetMapping("/movimientos")
    public Resources<Resource<Movimiento>> getMovimientos() {
        List<Movimiento> movimientoLista = movimientoService.findAll();
        List<Resource<Movimiento>> movimientoResources =
                movimientoLista.stream()
                        .map(MovimientoHATEOASController::createMovimientoResource)
                        .collect(Collectors.toList());
        Link selfRel = linkTo(methodOn(MovimientoHATEOASController.class)
                .getMovimientos()).withSelfRel();
        return new Resources<>(movimientoResources, selfRel);
    }

    @GetMapping("/movimientos/{idMovimiento}")
    public Resource<Movimiento> findMovimiento(@PathVariable("idMovimiento") Long idMovimiento) {
        Movimiento movimiento = movimientoService.findById(idMovimiento);
        Link selfLink = linkTo(methodOn(MovimientoHATEOASController.class)
                .findMovimiento(idMovimiento)).withSelfRel();
        Resource<Movimiento> movimientoResource = new Resource<>(movimiento,selfLink);
        movimientoResource.add(createPersonaLink(movimiento.getPersona().getId()));
        return movimientoResource;
    }

    private static Resource<Movimiento> createMovimientoResource(Movimiento e) {
        Link movimientoLink = linkTo(methodOn(PersonaController.class)
                .findUser(e.getId())).withSelfRel();
        return new Resource<>(e, movimientoLink);
    }

    private static Link createPersonaLink(long idUser) {
        return linkTo(methodOn(PersonaHATEOASController.class)
                .findUser(idUser)).withRel("persona");
    }
}
