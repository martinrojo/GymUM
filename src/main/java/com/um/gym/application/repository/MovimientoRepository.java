package com.um.gym.application.repository;


import com.um.gym.application.models.Movimiento;
import com.um.gym.application.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findAllByPersona(Persona persona);

    @Query("SELECT m FROM Movimiento m WHERE m.fechaSalida = NULL AND m.persona = :id_persona")
    Movimiento findByIdPersona(@Param("id_persona") Long id_persona);
}
