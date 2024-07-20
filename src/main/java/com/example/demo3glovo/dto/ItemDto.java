package com.example.demo3glovo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private int id;
    private double price;
    private int quantity;
    private int produkt;
}
