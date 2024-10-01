package com.gestor.tienda.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gestor.tienda.Entity.Empleado;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EmpleadoService empleadoService;

    @Override
    public void run(String... args) throws Exception {
        if (!empleadoService.existsByDni("admin")) {
            Empleado admin = new Empleado("Admin", "Admin", "admin", "123456789", "admin@example.com", "Domicilio", "admin", "admin123", "ADMIN");
            empleadoService.save(admin);
            System.out.println("Empleado admin creado con éxito.");
        }
    
        // Crear un empleado estándar para pruebas con rol USER
        if (!empleadoService.existsByDni("user1")) {
            Empleado user = new Empleado("User", "User", "user1", "987654321", "user1@example.com", "Domicilio", "user1", "user123", "USER");
            empleadoService.save(user);
            System.out.println("Empleado user1 creado con éxito.");
        }
    }
    
}
