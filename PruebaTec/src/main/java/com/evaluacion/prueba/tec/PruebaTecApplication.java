package com.evaluacion.prueba.tec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class PruebaTecApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaTecApplication.class, args);
		System.out.println("Hola");
	}
	
}
