package com.gestor.tienda.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente extends Persona{   

    //@OneToMany(mappedBy = "cliente")
    //@JsonBackReference
    //private List<Vehiculo> vehiculo;
    //private LocalDate fecha;

    public Cliente(String nombre, String apellido, String dni, String telefono, String email, String domicilio/*LocalDate fecha*/){
        super(nombre, apellido, dni, telefono, email, domicilio);
        //this.fecha = fecha;
    }
}