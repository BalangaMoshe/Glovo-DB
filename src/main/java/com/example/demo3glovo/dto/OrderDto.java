package com.example.demo3glovo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private int id;
    private String userName;
    private LocalDate checkoutDate;
    private double totalPrice;
    private List<Integer> items;
}
