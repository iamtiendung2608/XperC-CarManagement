package com.example.demo.repo;

import com.example.demo.model.Car;
import com.example.demo.model.Manufacture;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/*
 * Interface for manage data from Spring entity to Database Table manufacture
 * Extends PagingAndSortingRepository for easy pagination
 * */
@Repository
public interface ManufactureRepo extends PagingAndSortingRepository<Manufacture, Integer> {
}
