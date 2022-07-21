package com.example.demo.service;

import com.example.demo.dto.CarDTO;
import com.example.demo.exception.CarNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.model.Manufacture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
/*
* Interface describe main feature of management car
*/
public interface CarService {
    /* Find card in database with paging */
    List<CarDTO> findAllCar(Pageable pageable);
    /* Find card in database by car's name */
    List<CarDTO>findByCarName(String carName, Pageable pageable);
    /* Find card in database having common manufacture */
    List<CarDTO>findByManufacture(Manufacture manufacture, Pageable pageable);
    /* Find card in database by ID */
    CarDTO findByID(int carId) throws CarNotFoundException;
    /* Update exist car in database */
    void editCar(CarDTO updateCar);
    /* Delete exist car in database by ID */
    void deleteCar(int carId);
}
