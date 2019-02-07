package com.um.gym.application.repository;


import com.um.gym.application.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findAllByDni(String dni);

    Usuario findByDni(String dni);

    @Query("SELECT u FROM Usuario u WHERE CONCAT(u.nombre, ' ', u.apellido) LIKE CONCAT('%',:name,'%')")
    List<Usuario> findAllByNombreAndApellido(@Param("name") String name);

}