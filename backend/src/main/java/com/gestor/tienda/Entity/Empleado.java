package com.gestor.tienda.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empleado")
@Getter
@Setter
@NoArgsConstructor
public class Empleado extends Persona {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;
    private String role;

    public Empleado(String nombre, String apellido, String dni, String telefono, String email, String domicilio, String username, String password, String role) {
        super(nombre, apellido, dni, telefono, email, domicilio);
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
