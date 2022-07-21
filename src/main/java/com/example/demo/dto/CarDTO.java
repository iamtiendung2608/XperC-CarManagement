package com.example.demo.dto;

import com.example.demo.model.Manufacture;
import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder

public class CarDTO {
    private int id;
    private String name;
    private Manufacture manufacture;
    private LocalDate buyDate;
}
