package com.example.demo.service;

import com.example.demo.dto.ManufactureDTO;
import com.example.demo.exception.ManufactureNotFoundException;
import com.example.demo.mapper.ManufactureMapper;
import com.example.demo.model.Manufacture;
import com.example.demo.model.QManufacture;
import com.example.demo.repo.ManufactureRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
/*
* Implement ManufactureService feature
*/
@Transactional
@Service
@RequiredArgsConstructor
public class ManufactureServiceImp implements ManufactureService  {

    private final ManufactureRepo manufactureRepo;

    private final ManufactureMapper manufactureMapper;

    @PersistenceContext
    private EntityManager entityManager;

    QManufacture qManufacture = QManufacture.manufacture;

    @Override
    public List<ManufactureDTO> findAllManufacture(Pageable pageable) {
        return manufactureMapper.ManuListToManuDTOList(manufactureRepo.findAll(pageable).stream().toList());
    }

    @Override
    public void editManufacture(ManufactureDTO manufacture) {
        manufactureRepo.save(manufactureMapper.ManuDTOToManu(manufacture));
    }

    @Override
    public void deleteManufacture(int manufactureId) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        jpaQueryFactory.delete(qManufacture).where(qManufacture.id.eq(manufactureId)).execute();
    }

    @Override
    public ManufactureDTO findById(int id) throws ManufactureNotFoundException {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        Manufacture manufacture = jpaQueryFactory.selectFrom(qManufacture)
                .where(qManufacture.id.eq(id)).fetchOne();
        if(manufacture == null){
            throw new ManufactureNotFoundException();
        }
        return manufactureMapper.ManuToManuDTO(manufacture);
    }
}
