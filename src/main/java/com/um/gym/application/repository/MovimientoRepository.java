package com.um.gym.application.repository;


import com.um.gym.application.models.Movimiento;
import com.um.gym.application.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findAllByUsuario(Usuario usuario);
}
