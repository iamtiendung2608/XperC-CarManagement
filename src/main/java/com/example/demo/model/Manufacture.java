package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Manufacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacture", orphanRemoval = true)
    List<Car>carList = new ArrayList<>();

    public Manufacture(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
