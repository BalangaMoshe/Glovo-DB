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
                .name(itemEntity.getName())
                .produkt(itemEntity.getProdukt() != null ? itemEntity.getProdukt().getId() : 0)
                .build();
    }

    public static ItemEntity toEntity(ItemDto itemDto) {
        return ItemEntity.builder()
                .id(itemDto.getId())
                .price(itemDto.getPrice())
                .quantity(itemDto.getQuantity())
                .name(itemDto.getName())
                .produkt(itemDto.getProdukt() != 0 ? ProduktEntity.builder().id(itemDto.getProdukt()).build() : null)
                .build();
    }
}
