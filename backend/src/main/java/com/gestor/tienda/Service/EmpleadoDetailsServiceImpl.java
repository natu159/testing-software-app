package com.gestor.tienda.Service;

import com.gestor.tienda.Entity.Empleado;
import com.gestor.tienda.Entity.SecurityEmpleado;
import com.gestor.tienda.Repository.EmpleadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpleadoDetailsServiceImpl implements UserDetailsService {

    private final EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Empleado> empleadoOptional = empleadoRepository.findByUsername(username);
        Empleado empleado = empleadoOptional.orElseThrow(() -> new UsernameNotFoundException("Empleado not found"));
        return new SecurityEmpleado(empleado);
    }
}
