/*
* Controller for car management
* */

package com.example.demo.controller;

import com.example.demo.dto.CarDTO;
import com.example.demo.exception.CarNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.model.Manufacture;
import com.example.demo.service.CarServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
     * @param pageable: request param include sort, pagesize, pagenumber
     * @param name: Car's name for searching
     * @return ResponseEntity contains List of card in database if no searching keyword or
     * list of car has name like input
     * */
    @GetMapping("/car")
    public ResponseEntity<List<CarDTO>>getAllCar(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                                                 Pageable pageable){
        if(!name.equals("")){
            return ResponseEntity.ok().body(carServiceImp.findByCarName(name,pageable));
        }
        return ResponseEntity.ok().body(carServiceImp.findAllCar(pageable));
    }

    /**
     * Get list of car by their manufacture mapping ManyToOne
     * @param manufacture: manufacture mapping OneToMany with car
     * @param pageable: request param include sort, pagesize, pagenumber
     * @return ResponseEntity with a List of car having Manufacture like in request body with paging
     * */
    @PostMapping("/car/find")
    public ResponseEntity<List<CarDTO>>findByManufacture(@RequestBody Manufacture manufacture,
                                                         Pageable pageable) {
        List<CarDTO>findResult = carServiceImp.findByManufacture(manufacture, pageable);
        return ResponseEntity.ok().body(findResult);
    }

    /**
     * Add new Car to database
     * @param newCar: Data from @RequestBody
     * @return HttpStatus 201, indicate that a car created successfully
     * */
    @PostMapping("/car/add")
    public ResponseEntity<String>addNewCar(@RequestBody CarDTO newCar){
        carServiceImp.editCar(newCar);
        return new ResponseEntity<>("New car create success",HttpStatus.NO_CONTENT);
    }

    /**
     * Get Car need to be updated
     * @param cardId as path parameter
     * @exception CarNotFoundException if user try to update non exist car
     * @return HttpStatus 302, indicate that a car update successfully
     * */
    @GetMapping("/car/update/{id}")
    public ResponseEntity<CarDTO>getUpdateCar(@PathVariable("id")int cardId) {
        try {
            CarDTO car = carServiceImp.findByID(cardId);
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
    public ResponseEntity<String>updateCar(@RequestBody CarDTO updateCar) {
        carServiceImp.editCar(updateCar);
        return new ResponseEntity<>("Update car success",HttpStatus.NO_CONTENT);
    }

    /**
     * Delete Car exist in database by updateCar and id
     * @param id: id need deleted
     * @exception CarNotFoundException if user try to update non exist car
     * @return HttpStatus 200, indicate that a car delete successfully
     * */
    @DeleteMapping("/car/delete/{id}")
    public ResponseEntity<String>deleteCar( @PathVariable("id")int id)  {
        carServiceImp.deleteCar(id);
        return new ResponseEntity<>("Delete car success",HttpStatus.NO_CONTENT);
    }



}
