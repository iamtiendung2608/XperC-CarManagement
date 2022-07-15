package com.example.demo.service;

import com.example.demo.exception.CarNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.model.Manufacture;
import org.springframework.stereotype.Service;

import java.util.List;
/*
* Interface describe main feature of management car
*/
public interface CarService {
    /* Find card in database with paging */
    List<Car>findAllCar(int pageNum);
    /* Find card in database by car's name */
    List<Car>findByCarName(String carName, int pageNum);
    /* Find card in database having common manufacture */
    List<Car>findByManufacture(Manufacture manufacture, int pageNum);
    /* Find card in database by ID */
    Car findByID(int carId) throws CarNotFoundException;
    /* Add new car to database */
    void addNewCar(Car newCar);
    /* Update exist car in database */
    void editCar(Car updateCar) throws CarNotFoundException;
    /* Delete exist car in database by ID */
    void deleteCar(int carId) throws CarNotFoundException;
}
