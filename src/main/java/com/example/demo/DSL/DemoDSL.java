package com.example.demo.DSL;

import com.example.demo.model.QCar;
import com.example.demo.model.QManufacture;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DemoDSL {
    @PersistenceContext
    EntityManager em;

    JPAQueryFactory jpaQueryFactory;

    QCar qCar = QCar.car;

    QManufacture qManufacture = QManufacture.manufacture;

    public DemoDSL(EntityManager em) {
        this.em = em;
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }
}
