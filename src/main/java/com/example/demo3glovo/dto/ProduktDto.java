package com.example.demo3glovo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProduktDto {
    private Integer id;
    private String name;
    private String country;
    private double price;

}
