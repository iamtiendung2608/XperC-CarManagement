package com.example.demo.service;

import com.example.demo.exception.CarNotFoundException;
import com.example.demo.exception.ManufactureNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.model.Manufacture;

import java.util.List;
/*
 * Interface describe main feature of management manufacture
 */
public interface ManufactureService {
    /* Find all manufacture with paging */
    List<Manufacture> findAllManufacture(int pageNum);
    /* Add new manufacture to database */
    void addManufacture(Manufacture manufacture);
    /* Update exist manufacture to database */
    void editManufacture(Manufacture manufacture) throws ManufactureNotFoundException;
    /* Delete exist manufacture from database by manufactureId */
    void deleteManufacture(int manufactureId) throws ManufactureNotFoundException;
    /* Find exist manufacture from database by manufactureId */
    Manufacture findById(int id) throws ManufactureNotFoundException;
}
