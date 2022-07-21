package com.example.demo.repo;

import com.example.demo.model.Car;
import com.example.demo.model.Manufacture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
* Interface for manage data from Spring entity to Database Table car
* Extends PagingAndSortingRepository for easy pagination
* */
@Repository
public interface CarRepo extends PagingAndSortingRepository<Car,Integer> {
    /**
     * Find all car by their name
     * @param name: car's name
     * @param pageable: create paging result
     */
    Page<Car> findAllByName(String name, Pageable pageable);
    /**
     * Find all car by their manufacture
     * @param manufacture: manufacture
     * @param pageable: create paging result
     */
    Page<Car>findAllByManufacture(Manufacture manufacture, Pageable pageable);
}

