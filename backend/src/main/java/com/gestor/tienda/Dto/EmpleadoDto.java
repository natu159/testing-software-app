package com.gestor.tienda.Dto;



import com.gestor.tienda.Entity.Persona;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmpleadoDto extends Persona{   
    //@NotNull
    //private LocalDate fecha;
    public EmpleadoDto(@NotBlank String nombre,@NotBlank String apellido,@NotBlank String ciudad,@NotBlank String telefono,@NotBlank String email,@NotBlank String domicilio /*LocalDate fecha*/) {
        super(nombre, apellido, ciudad, telefono, email, domicilio);
        //this.fecha = fecha;
    }
}