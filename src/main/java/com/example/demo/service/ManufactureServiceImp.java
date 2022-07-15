package com.example.demo.service;

import com.example.demo.exception.ManufactureNotFoundException;
import com.example.demo.model.Manufacture;
import com.example.demo.repo.ManufactureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
/*
* Implement ManufactureService feature
*/
@Transactional
@Service
public class ManufactureServiceImp implements ManufactureService {
    @Autowired
    private ManufactureRepo manufactureRepo;

    @Override
    public List<Manufacture> findAllManufacture(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum,2);
        return manufactureRepo.findAll(pageable).stream().toList();
    }

    @Override
    public void addManufacture(Manufacture manufacture) {
        manufactureRepo.save(manufacture);
    }

    @Override
    public void editManufacture(Manufacture manufacture) {
       manufactureRepo.save(manufacture);
    }

    @Override
    public void deleteManufacture(int manufactureId) throws ManufactureNotFoundException {
        Optional<Manufacture> currentManufacture = manufactureRepo.findById(manufactureId);
        if(!currentManufacture.isPresent()){
            throw new ManufactureNotFoundException();
        }
        manufactureRepo.delete(currentManufacture.get());
    }

    @Override
    public Manufacture findById(int id) throws ManufactureNotFoundException {
        Optional<Manufacture> manufacture = manufactureRepo.findById(id);
        if(manufacture.isPresent()){
            return manufacture.get();
        }
        throw new ManufactureNotFoundException();
    }
}
