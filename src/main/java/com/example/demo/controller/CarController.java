/*
* Controller for car management
* */

package com.example.demo.controller;

import com.example.demo.exception.CarNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.model.Manufacture;
import com.example.demo.service.CarServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class handles 6 features: Add, delete, update,
 * paging data, search for car name and manufacture
 * */
@RestController
public class CarController {
    /* Call CarServiceImp */
    @Autowired
    private CarServiceImp carServiceImp;

    /**
     * Function for paging data return to server, if search keyword is not empty,
     * return search result
     * @param pageNumber: 8 car for each page, default page = 0
     * @param name: Car's name for searching
     * @return ResponseEntity contains List of card in database if no searching keyword or
     * list of car has name like input
     * */
    @GetMapping("/car")
    public ResponseEntity<List<Car>>getAllCar(@RequestParam(name = "p", required = false, defaultValue = "0")int pageNumber,
                                              @RequestParam(name = "name", required = false, defaultValue = "") String name){
        if(!name.equals("")){
            return ResponseEntity.ok().body(carServiceImp.findByCarName(name,pageNumber));
        }
        return ResponseEntity.ok().body(carServiceImp.findAllCar(pageNumber));
    }

    /**
     * Add new Car to database
     * @param newCar: Data from @RequestBody
     * @return HttpStatus 201, indicate that a car created successfully
     * */
    @PostMapping("/car/add")
    public ResponseEntity<String>addNewCar(@RequestBody Car newCar){
        carServiceImp.addNewCar(newCar);
        return new ResponseEntity<>("New car create success",HttpStatus.CREATED);
    }

    /**
     * Get Car need to be updated
     * @param cardId as path parameter
     * @exception CarNotFoundException if user try to update non exist car
     * @return HttpStatus 302, indicate that a car update successfully
     * */
    @GetMapping("/car/update/{id}")
    public ResponseEntity<Car>getUpdateCar(@PathVariable("id")int cardId)  {
        try {
            Car car = carServiceImp.findByID(cardId);
            return ResponseEntity.ok().body(car);
        } catch (CarNotFoundException e) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Update Car exist in database by updateCar
     * @param updateCar as car updated
     * @exception CarNotFoundException if user try to update non exist car
     * @return HttpStatus 302, indicate that a car update successfully
     * */
    @PutMapping("/car/update")
    public ResponseEntity<String>updateCar(@RequestBody Car updateCar)  {
        try {
            carServiceImp.editCar(updateCar);
        } catch (CarNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Update car success",HttpStatus.FOUND);
    }

    /**
     * Delete Car exist in database by updateCar and id
     * @param id: id need deleted
     * @exception CarNotFoundException if user try to update non exist car
     * @return HttpStatus 200, indicate that a car delete successfully
     * */
    @DeleteMapping("/car/delete/{id}")
    public ResponseEntity<String>deleteCar( @PathVariable("id")int id)  {
        try {
            carServiceImp.deleteCar(id);
        } catch (CarNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Delete car success",HttpStatus.OK);
    }
    /**
     * Get list of car by their manufacture mapping ManyToOne
     * @param manufacture: manufacture mapping OneToMany with car
     * @param pageNumber: 8 car for each page, default page = 0
     * @return ResponseEntity with a List of car having Manufacture like in request body with paging
     * */
    @PostMapping("/car/find")
    public ResponseEntity<?>findByManufacture(@RequestBody Manufacture manufacture,
                                              @RequestParam(name = "p", required = false, defaultValue = "0")int pageNumber){
        List<Car>findResult = carServiceImp.findByManufacture(manufacture,pageNumber);
        return ResponseEntity.ok().body(findResult);
    }

}
