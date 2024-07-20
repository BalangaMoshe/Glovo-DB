package com.example.demo3glovo.converter;

import com.example.demo3glovo.dto.ItemDto;
import com.example.demo3glovo.entity.ItemEntity;
import com.example.demo3glovo.entity.ProduktEntity;

public class ItemConverter {
    public static ItemDto toDto(ItemEntity itemEntity) {
        return ItemDto.builder()
                .id(itemEntity.getId())
                .price(itemEntity.getPrice())
                .quantity(itemEntity.getQuantity())
                .produkt(itemEntity.getProdukt().getId())
                .build();
    }

    public static ItemEntity toEntity(ItemDto itemDto) {
        return ItemEntity.builder()
                .produkt(ProduktEntity.builder().id(itemDto.getProdukt()).build())
                .quantity(itemDto.getQuantity())
                .id(itemDto.getId())
                .price(itemDto.getPrice())
                .build();
    }
}
