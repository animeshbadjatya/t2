package com.ecommerce.teamviewerecommerce;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Teamviewer E-commerce App REST APIs",
				description = "E-commerce App REST APIs Documentation",
				version = "v1",
			//	summary = "A take home challenge from Teamviewer.",
				contact = @Contact(
						name = "Animesh Badjatya",
						email = "badjatiya.animesh@gmail.com",
						url = "#"
				),
				termsOfService = "Terms of Service"
		),
		externalDocs = @ExternalDocumentation(
				description = " Teamviewer e-commerce App REST APIs Documentation"
		)
)
public class TeamviewerEcommerceApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(TeamviewerEcommerceApplication.class, args);
	}

}
