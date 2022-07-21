package com.example.demo.service;

import com.example.demo.dto.ManufactureDTO;
import com.example.demo.exception.CarNotFoundException;
import com.example.demo.exception.ManufactureNotFoundException;
import com.example.demo.model.Car;
import com.example.demo.model.Manufacture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
/*
 * Interface describe main feature of management manufacture
 */
public interface ManufactureService {
    /* Find all manufacture with paging */
    List<ManufactureDTO> findAllManufacture(Pageable pageable);
    /* Update exist manufacture to database */
    void editManufacture(ManufactureDTO manufacture);
    /* Delete exist manufacture from database by manufactureId */
    void deleteManufacture(int manufactureId);
    /* Find exist manufacture from database by manufactureId */
    ManufactureDTO findById(int id) throws ManufactureNotFoundException;
}
