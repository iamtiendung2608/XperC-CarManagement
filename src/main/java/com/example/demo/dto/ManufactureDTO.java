package com.example.demo.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class ManufactureDTO {
    private int id;

    private String name;

    private String address;
}
