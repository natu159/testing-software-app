package com.gestor.tienda.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestor.tienda.Dto.EmpleadoDto;
import com.gestor.tienda.Entity.Empleado;
import com.gestor.tienda.Service.EmpleadoService;

@RestController
@RequestMapping("/empleado")
@CrossOrigin("*")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/list")
    public ResponseEntity<List<Empleado>> findAll() {
        List<Empleado> list = empleadoService.findAll();
        //list.forEach(empleado -> Hibernate.initialize(empleado.getVehiculo()));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EmpleadoDto empleadoDto){
        String dniEmpleadoNuevo = empleadoDto.getDni();
        if(empleadoDto.getNombre().isBlank() ||
        empleadoDto.getApellido().isBlank() ||
        empleadoDto.getDomicilio().isBlank() ||
        empleadoDto.getDni().isBlank() ||
        empleadoService.existsByDni(dniEmpleadoNuevo)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            
        }else{

            Empleado empleadoNuevo = new Empleado(
            empleadoDto.getNombre(),
            empleadoDto.getApellido(),
            empleadoDto.getDni(),
            empleadoDto.getTelefono(),
            empleadoDto.getEmail(),
            empleadoDto.getDomicilio()
            //empleadoDto.getFecha()
            );

            empleadoService.save(empleadoNuevo);
            return new ResponseEntity<>(HttpStatus.OK);   
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody EmpleadoDto empleadoDto){
        if(!empleadoService.existsById(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Empleado empleado = empleadoService.findById(id).get();

        empleado.setNombre(empleadoDto.getNombre());
        empleado.setApellido(empleadoDto.getApellido());
        empleado.setDni(empleadoDto.getDni());
        empleado.setTelefono(empleadoDto.getTelefono());
        empleado.setEmail(empleadoDto.getEmail());
        empleado.setDomicilio(empleadoDto.getDomicilio());
        //cliente.setFecha(clienteDto.getFecha());

        empleadoService.save(empleado);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){

        if(!empleadoService.existsById(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        empleadoService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listByDni/{dni}")
    public ResponseEntity<Optional<Empleado>> findByDni(@PathVariable String dni){

        try {
            Optional<Empleado> empleado = empleadoService.findByDni(dni);
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }          
    }

    @GetMapping("/listById/{id}")
    public ResponseEntity<Optional<Empleado>> findById(@PathVariable int id){

        try {
            Optional<Empleado> empleado = empleadoService.findById(id);
            return new ResponseEntity<>(empleado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }        
    }
    @GetMapping("/listByNombre/{nombre}")
    public ResponseEntity<List<Empleado>> findByNombre(@PathVariable String nombre) {
        try {
            List<Empleado> empleados = empleadoService.findByNombre(nombre); // Fix here
            return new ResponseEntity<>(empleados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}