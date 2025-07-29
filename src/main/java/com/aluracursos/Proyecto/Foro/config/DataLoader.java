package com.aluracursos.Proyecto.Foro.config;

import com.aluracursos.Proyecto.Foro.domain.user.User;
import com.aluracursos.Proyecto.Foro.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Solo crear usuarios si no existen
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User("admin", passwordEncoder.encode("123456"), "admin@test.com");
            userRepository.save(admin);
            System.out.println("Usuario admin creado con contraseña: 123456");
        }

        if (userRepository.findByUsername("user1") == null) {
            User user1 = new User("user1", passwordEncoder.encode("123456"), "user1@test.com");
            userRepository.save(user1);
            System.out.println("Usuario user1 creado con contraseña: 123456");
        }
    }
}