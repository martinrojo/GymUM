package com.um.gym.application.service.impl;

import com.um.gym.application.ExceptionHandler.MyResourceNotFoundException;
import com.um.gym.application.models.Movimiento;
import com.um.gym.application.models.Persona;
import com.um.gym.application.repository.MovimientoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class MovimientoServiceImpl extends ServiceImpl<Movimiento, Long> {

    private static final Logger logger = LoggerFactory.getLogger(MovimientoServiceImpl.class);

    @Autowired
    private MovimientoRepository dao;

    @Autowired
    private PersonaServiceImpl personaService;

    @Override
    public Movimiento create(Movimiento movimiento) {
        List<Movimiento> movimientos = dao.findAllByPersona(movimiento.getPersona());
        if (movimientos != null) {
            for (Movimiento m : movimientos) {
                if (m.getFechaSalida() == null) {
                    logger.warn("POST | Hay un movimiento sin terminar para el usuario " + movimiento.getPersona().getId());
                    throw new MyResourceNotFoundException("Hay un movimiento sin terminar para el usuario " + movimiento.getPersona().getId());
                }
            }
        }
        try {
            logger.info("POST | Movimiento creado.");

            Calendar calendario = GregorianCalendar.getInstance();
            Date fecha = calendario.getTime();
            movimiento.setFechaEntrada(fecha);
            logger.info(movimiento.toString());

            return super.create(movimiento);
        } catch (Exception e) {
            logger.error("POST | No hay resultados para esa busqueda.  " + e.getMessage());
            throw new MyResourceNotFoundException("No hay resultados para esa busqueda. " + e.getMessage());
        }
    }

    public ResponseEntity delete(Long id) {
        try {
            super.deleteById(id);
            logger.info("DELETE | Movimiento eliminado.");
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (Exception e) {
            logger.error("DELETE | No hay resultados para esa busqueda.  " + e.getMessage());
            throw new MyResourceNotFoundException("No hay resultados para esa busqueda.  " + e.getMessage());
        }
    }


    public Movimiento update(Long idPersona) {
        try {
            Movimiento movimiento = findByIdPersona(idPersona);
            logger.info("GET | " + movimiento.toString());
            Date fecha = new Date();
            movimiento.setFechaSalida(fecha);
            logger.info(movimiento.toString());

            Date fechaMenor = movimiento.getFechaEntrada();
            Date fechaMayor = movimiento.getFechaSalida();


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

            Persona persona = personaService.findById(movimiento.getPersona().getId());


            if (persona.getMinutos() + m >= 60){
                Integer m2 = (persona.getMinutos() + m) - 60;
                persona.setHoras(persona.getHoras() + h + 1) ;
                persona.setMinutos(m2);
            } else {
                persona.setHoras(persona.getHoras() + h);
                persona.setMinutos(persona.getMinutos() + m);
            }
            logger.info("PUT | Movimiento actualizado.");
            personaService.update(persona);
            return super.update(movimiento);

        } catch (Exception e) {
            logger.error("PUT | No hay resultados para esa busqueda.  " + e.getMessage());
            throw new MyResourceNotFoundException("No hay resultados para esa busqueda. " + e.getMessage());
        }
    }

    @Override
    public Movimiento findById(Long id) {
        if (super.findById(id) == null) {
            logger.warn("GET | No existe el movimiento con id: " + id);
            throw new MyResourceNotFoundException("No existe Movimiento con id: " + id);
        }
        try {
            Movimiento m = super.findById(id);
            logger.info("GET | " + m.toString());
            return m;
        } catch (Exception e) {
            logger.error("GET | No hay resultados para esa busqueda.  " + e.getMessage());
            throw new MyResourceNotFoundException("No hay resultados para esa busqueda. " + e.getMessage());
        }
    }

    @Override
    public List<Movimiento> findAll() {
        try {
            List<Movimiento> lista = super.findAll();
            logger.info("GET | " + lista.toString());
            return lista;
        } catch (Exception e) {
            logger.error("GET | No hay resultados para esa busqueda.  " + e.getMessage());
            throw new MyResourceNotFoundException("No hay resultados para esa busqueda.  " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }

    public List<Movimiento> findByPersona(Long id){
        return dao.findAllByPersona(personaService.findById(id));
    }

    public Movimiento findByIdPersona(Long idPersona){
        if (dao.findAllByPersona(personaService.findById(idPersona)).isEmpty()) {
            logger.warn("GET | No existe Movimientos para la persona: \" + idPersona");
            throw new MyResourceNotFoundException("No existe Movimientos para la persona: " + idPersona);
        }
        try {
            List<Movimiento> movimientos = personaService.findById(idPersona).getMovimientos();
            for (Movimiento m : movimientos) {
                if (m.getFechaSalida() == null) {
                    logger.info("GET | " + m.toString());
                    return m;
                }
            }
            logger.warn("GET | No hay movimiento creado.");
            throw new MyResourceNotFoundException("No hay Movimientos creados para la persona:" + idPersona);
        } catch (Exception e) {
            logger.error("GET | No hay resultados para esa busqueda.  " + e.getMessage());
            throw new MyResourceNotFoundException("No hay resultados para esa busqueda.  " + e.getMessage());
        }
    }


}
