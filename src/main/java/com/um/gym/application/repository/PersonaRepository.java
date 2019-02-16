package com.um.gym.application.repository;


import com.um.gym.application.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    List<Persona> findAllByDni(String dni);

    Persona findByDni(String dni);

    Persona findByEmail(String email);

    @Query("SELECT u FROM Persona u WHERE CONCAT(u.nombre, ' ', u.apellido) LIKE CONCAT('%',:name,'%')")
    List<Persona> findAllByNombreAndApellido(@Param("name") String name);

}