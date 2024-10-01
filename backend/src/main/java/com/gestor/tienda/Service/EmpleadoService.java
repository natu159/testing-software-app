package com.gestor.tienda.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gestor.tienda.Entity.Empleado;
import com.gestor.tienda.Repository.EmpleadoRepository;


@Service
@Transactional
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    PasswordEncoder passwordEncoder; // Inyecta el PasswordEncoder

    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    public void save(Empleado empleado) {
        // Verificar si el rol del empleado está vacío o es nulo, asignar "USER" por defecto
        if (empleado.getRole() == null || empleado.getRole().isEmpty()) {
            empleado.setRole("USER");
        }

        // Codifica la contraseña antes de guardarla
        empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));

        // Guarda el empleado en el repositorio
        empleadoRepository.save(empleado);
    }

    public void deleteById(int id) {
        empleadoRepository.deleteById(id);
    }

    public Optional<Empleado> findById(int id) {
        return empleadoRepository.findById(id);
    }

    public Optional<Empleado> findByDni(String dni) {
        return empleadoRepository.findByDni(dni);
    }

    public List<Empleado> findByNombre(String nombre) {
        return empleadoRepository.findByNombre(nombre);
    }

    public boolean existsById(int id) {
        return empleadoRepository.existsById(id);
    }

    public boolean existsByDni(String dni) {
        return empleadoRepository.existsByDni(dni);
    }
}
