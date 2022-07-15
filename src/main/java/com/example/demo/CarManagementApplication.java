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

	/* Init data when run server in first time */
	@Bean
	public CommandLineRunner initData(CarRepo carRepo, ManufactureRepo manufactureRepo){
		return args -> {
//			Manufacture Honda =	new Manufacture("Honda", "23 Ly Thuong Kiet - P9 - Q10");
//			Manufacture Toyota = new Manufacture("Toyota", "23 Nguyen Van Cu - Q5");
//			manufactureRepo.save(Honda);
//			manufactureRepo.save(Toyota);
//
//			List<Car>carList = Arrays.asList(
//					new Car("Car 1",Honda, LocalDate.of(2022,1,27)),
//					new Car("Car 2",Honda, LocalDate.of(2020,12,12)),
//					new Car("Car 2",Toyota, LocalDate.of(2022,11,20)),
//					new Car("Car 1",Toyota, LocalDate.of(2022,10,7)),
//					new Car("Car 999",Honda, LocalDate.of(2022,8,2)),
//					new Car("Car 5",Toyota, LocalDate.of(2022,4,12)),
//					new Car("Car 3",Toyota, LocalDate.of(2019,5,15)),
//					new Car("Car 4",Toyota, LocalDate.of(2025,7,25)),
//					new Car("Car 6",Toyota, LocalDate.of(2012,8,10))
//					);
//			carList.forEach(element -> carRepo.save(element));
		};
	}
}
