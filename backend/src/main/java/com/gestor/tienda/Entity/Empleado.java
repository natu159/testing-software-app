package com.gestor.tienda.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Empleado extends Persona{   

    //@OneToMany(mappedBy = "empleado")
    //@JsonBackReference
    //private List<Vehiculo> vehiculo;
    //private LocalDate fecha;

    public Empleado(String nombre, String apellido, String ciudad, String telefono, String email, String domicilio/*LocalDate fecha*/){
        super(nombre, apellido, ciudad, telefono, email, domicilio);
        //this.fecha = fecha;
    }
}