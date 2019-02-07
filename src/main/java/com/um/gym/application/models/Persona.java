package com.um.gym.application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

@Entity
@Table(name = "usuario")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "apellido", length = 50)
    private String apellido;

    @Column(name = "dni", length = 8)
    private String dni;

    @Column(name = "email", length = 90)
    @Email(message = "Ingrese un email correcto.")
    private String email;

    @Column(name = "horas")
    private Integer horas;

    @Column(name = "minutos")
    private Integer minutos;

    @JsonIgnore
    @OneToMany(mappedBy = "persona")
    private Set<Movimiento> movimientos;

    public Persona() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Set<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(id, persona.id) &&
                Objects.equals(nombre, persona.nombre) &&
                Objects.equals(apellido, persona.apellido) &&
                Objects.equals(dni, persona.dni) &&
                Objects.equals(email, persona.email) &&
                Objects.equals(movimientos, persona.movimientos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, dni, email, movimientos);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}