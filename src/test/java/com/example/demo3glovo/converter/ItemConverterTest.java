package com.example.demo3glovo.converter;

import com.example.demo3glovo.dto.ItemDto;
import com.example.demo3glovo.entity.ItemEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemConverterTest {

    @Test
    public void toDtoTest() {
        ItemEntity itemEntity = ItemEntity.builder()
                .id(1)
                .name("Pizza")
                .quantity(2)
                .price(10.0)
                .build();

        ItemDto itemDto = ItemConverter.toDto(itemEntity);

        assertEquals(itemEntity.getId(), itemDto.getId());
        assertEquals(itemEntity.getName(), itemDto.getName());
        assertEquals(itemEntity.getQuantity(), itemDto.getQuantity());
        assertEquals(itemEntity.getPrice(), itemDto.getPrice());
    }

    @Test
    public void toEntityTest() {
        ItemDto itemDto = ItemDto.builder()
                .id(1)
                .name("Pizza")
                .quantity(2)
                .price(10.0)
                .build();

        ItemEntity itemEntity = ItemConverter.toEntity(itemDto);

        assertEquals(itemDto.getId(), itemEntity.getId());
        assertEquals(itemDto.getName(), itemEntity.getName());
        assertEquals(itemDto.getQuantity(), itemEntity.getQuantity());
        assertEquals(itemDto.getPrice(), itemEntity.getPrice());
    }
}
