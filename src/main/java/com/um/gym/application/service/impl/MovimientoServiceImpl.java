package com.um.gym.application.service.impl;

import com.um.gym.application.models.Movimiento;
import com.um.gym.application.models.Persona;
import com.um.gym.application.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MovimientoServiceImpl extends ServiceImpl<Movimiento, Long> {

    @Autowired
    private MovimientoRepository dao;

    @Autowired
    private PersonaServiceImpl personaService;

    @Override
    public Movimiento create(Movimiento entity) {
        return super.create(entity);
    }

    @Override
    public void delete(Movimiento entity) {
        super.delete(entity);
    }

    @Override
    public Movimiento update(Movimiento entity) {
        Date fechaMenor = entity.getFechaEntrada();
        Date fechaMayor = entity.getFechaSalida();


        //los milisegundos
        long diferenciaMils = fechaMayor.getTime() - fechaMenor.getTime();
        //obtenemos los segundos
        long segundos = diferenciaMils / 1000;
        //obtenemos las horas
        long horas = segundos / 3600;
        //restamos las horas para continuar con minutos
        segundos -= horas*3600;
        //igual que el paso anterior
        long minutos = segundos /60;
        segundos -= minutos*60;

        Integer h = (int) horas;
        Integer m = (int) minutos;

        Persona persona = personaService.findById(entity.getPersona().getId());


        if (persona.getMinutos() + m >= 60){
            Integer m2 = (persona.getMinutos() + m) - 60;
            persona.setHoras(persona.getHoras() + h + 1) ;
            persona.setMinutos(m2);
        } else {
            persona.setHoras(persona.getHoras() + h);
            persona.setMinutos(persona.getMinutos() + m);
        }

        personaService.update(persona);
        return super.update(entity);
    }

    @Override
    public Movimiento findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public List<Movimiento> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    public List<Movimiento> findByUsuario(Long id){
        return dao.findAllByPersona(personaService.findById(id));
    }

    public Movimiento findByIdPersona(Long idPersona){
        return dao.findByIdPersona(idPersona);
    }


}
