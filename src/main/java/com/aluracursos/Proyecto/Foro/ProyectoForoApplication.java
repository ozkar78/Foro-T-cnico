package com.aluracursos.Proyecto.Foro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.aluracursos.Proyecto.Foro.domain")
@EntityScan(basePackages = "com.aluracursos.Proyecto.Foro.domain")
public class ProyectoForoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoForoApplication.class, args);
	}

}
