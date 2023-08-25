package com.GestorUsuarios;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.GestorUsuarios.mapper")
@SpringBootApplication
public class GestorUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorUsuariosApplication.class, args);
	
	System.out.println("Funcionando (ᵔᴥᵔ) ");
	}

}
