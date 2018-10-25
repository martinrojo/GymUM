package com.um.gym.application.repository;


import com.um.gym.application.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findAllByDni(String dni);
    List<Usuario> findAllByNombre(String nombre);
    List<Usuario> findAllByApellido(String apellido);

}