package com.projeto.gerfuncionario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerfuncionarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerfuncionarioApplication.class, args);
		System.out.println("Sistema Iniciado!");
		System.out.println("Acesse: http://localhost:8080");
	}

}
