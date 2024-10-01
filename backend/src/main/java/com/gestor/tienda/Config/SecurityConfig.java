package com.gestor.tienda.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll() // Permitir acceso a rutas públicas
                        .requestMatchers("/empleado/**").hasAuthority("ADMIN") // Solo el ADMIN puede gestionar empleados
                        .anyRequest().authenticated() // Cualquier otro endpoint requiere autenticación
                )
                // Configuración del formulario de inicio de sesión
                .formLogin(Customizer.withDefaults()) // Esto lo dejaré aquí para que lo implementes más adelante
                /*
                .formLogin(form -> form
                        .loginPage("/login") // Ruta a la página de inicio de sesión
                        .permitAll() // Permitir que todos accedan a la página de inicio de sesión
                        .defaultSuccessUrl("/home", true) // Redirigir a la página principal después del inicio de sesión exitoso
                        .failureUrl("/login?error=true") // Redirigir a la página de inicio de sesión con un error en caso de fallo
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Ruta para cerrar sesión
                        .logoutSuccessUrl("/login?logout=true") // Redirigir a la página de inicio de sesión después de cerrar sesión
                        .permitAll() // Permitir que todos accedan a la ruta de cierre de sesión
                )
                */
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
