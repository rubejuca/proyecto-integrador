package com.rubejuca.proyectointegrador;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProyectoIntegradorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoIntegradorApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
						.addMapping("/**")
						.allowedOrigins("http://localhost:8081")
						.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
								HttpMethod.DELETE.name(), HttpMethod.PATCH.name());
			}
		};
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new io.swagger.v3.oas.models.info.Info()
						.title("SITAP - Proyecto Integrador API")
						.version("1.0.0")
						.description("Sistema de Información para Tratamiento de Adicción a la Pornografía")
						.termsOfService("http://swagger.io/terms/")
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.org")));
	}
}
