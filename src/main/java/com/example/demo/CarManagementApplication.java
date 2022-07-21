package com.example.demo;

import com.example.demo.model.Car;
import com.example.demo.model.Manufacture;
import com.example.demo.repo.CarRepo;
import com.example.demo.repo.ManufactureRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.RequestHandlerCombiner;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class CarManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarManagementApplication.class, args);
	}

	/* Configure Swagger */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo")).build();
	}
}
