package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    private Manufacture manufacture;
    private LocalDate buyDate;

    public Car(String name, Manufacture manufacture, LocalDate buyDate) {
        this.name = name;
        this.manufacture = manufacture;
        this.buyDate = buyDate;
    }
}
