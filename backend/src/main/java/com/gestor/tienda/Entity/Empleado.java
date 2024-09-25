package com.gestor.tienda.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Empleado extends Persona {
    public Empleado(String nombre, String apellido, String dni, String telefono, String email, String domicilio) {
        super(nombre, apellido, dni, telefono, email, domicilio);
    }
}