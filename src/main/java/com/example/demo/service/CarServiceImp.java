package com.example.demo.service;

import com.example.demo.exception.CarNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.model.Manufacture;
import com.example.demo.repo.CarRepo;
import com.example.demo.repo.ManufactureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
/*
 * Implement CarService feature
 */
@Transactional
@Service
public class CarServiceImp implements CarService {
    private CarRepo carRepo;
    private ManufactureRepo manufactureRepo;

    @Autowired
    public CarServiceImp(CarRepo carRepo, ManufactureRepo manufactureRepo) {
        this.carRepo = carRepo;
        this.manufactureRepo = manufactureRepo;
    }

    @Override
    public List<Car> findAllCar(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum,8);
        return  carRepo.findAll(pageable).stream().toList();
    }

    @Override
    public List<Car> findByCarName(String carName, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum,8);
        return carRepo.findAllByName(carName,pageable);
    }

    @Override
    public List<Car> findByManufacture(Manufacture manufacture, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum,8);
        return carRepo.findAllByManufacture(manufacture, pageable);
    }

    @Override
    public Car findByID(int carId) throws CarNotFoundException {
        Optional<Car> currentCar = carRepo.findById(carId);
        if(!currentCar.isPresent()){
            throw new CarNotFoundException();
        }
        return currentCar.get();
    }

    @Override
    public void addNewCar(Car newCar) {
        carRepo.save(newCar);
    }

    @Override
    public void editCar(Car updateCar) throws CarNotFoundException {
        carRepo.save(updateCar);
    }

    @Override
    public void deleteCar(int carId) throws CarNotFoundException {
      Optional<Car> car = carRepo.findById(carId);
      if(!car.isPresent()){
          throw new CarNotFoundException();
      }
      carRepo.delete(car.get());
    }
}
