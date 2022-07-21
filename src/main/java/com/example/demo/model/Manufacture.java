package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
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

}
