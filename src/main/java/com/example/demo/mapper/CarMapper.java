package com.example.demo.mapper;

import com.example.demo.dto.CarDTO;
import com.example.demo.model.Car;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDTO CarToCarDTO(Car car);
    Car CarDTOToCar(CarDTO carDTO);
    @Mapping(target = "id", ignore = true)
    List<CarDTO>CarListToCarDTOList(List<Car> cars);
}
