package com.example.demo.service;

import com.example.demo.dto.CarDTO;
import com.example.demo.exception.CarNotFoundException;
import com.example.demo.mapper.CarMapper;
import com.example.demo.model.Car;
import com.example.demo.model.Manufacture;
import com.example.demo.model.QCar;
import com.example.demo.repo.CarRepo;
import com.example.demo.repo.ManufactureRepo;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/*
 * Implement CarService feature
 */
@Transactional
@Service
@RequiredArgsConstructor
public class CarServiceImp implements CarService  {
    private final CarRepo carRepo;

    @PersistenceContext
    private EntityManager entityManager;
    QCar qCar = QCar.car;

    private final CarMapper carMapper;

    @Override
    public List<CarDTO> findAllCar(Pageable pageable) {
        return carMapper.CarListToCarDTOList(carRepo.findAll(pageable).stream().toList());
    }

    @Override
    public List<CarDTO> findByCarName(String carName, Pageable pageable) {
        return carMapper.CarListToCarDTOList(carRepo.findAllByName(carName,pageable).stream().toList());
    }

    @Override
    public List<CarDTO> findByManufacture(Manufacture manufacture, Pageable pageable) {
        return carMapper.CarListToCarDTOList(carRepo.findAllByManufacture(manufacture,pageable).stream().toList());
    }

    @Override
    public CarDTO findByID(int carId) throws CarNotFoundException {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        Car car = jpaQueryFactory.selectFrom(qCar).where(qCar.id.eq(carId)).fetchOne();
        return carMapper.CarToCarDTO(car);
    }

    @Override
    public void editCar(CarDTO updateCar) {
        carRepo.save(carMapper.CarDTOToCar(updateCar));
    }

    @Override
    public void deleteCar(int carId) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        jpaQueryFactory.delete(qCar).where(qCar.id.eq(carId)).execute();
    }
}
