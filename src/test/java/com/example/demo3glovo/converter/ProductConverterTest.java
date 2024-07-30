package com.example.demo3glovo.converter;

import com.example.demo3glovo.dto.ProduktDto;
import com.example.demo3glovo.entity.ProduktEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductConverterTest {

    @Test
    public void toDtoTest() {
        ProduktEntity produktEntity = ProduktEntity.builder()
                .id(1)
                .name("Torte")
                .country("Ukraine")
                .price(75.50)
                .build();

        ProduktDto produktDto = ProductConverter.toDto(produktEntity);

        assertEquals(produktEntity.getId(), produktDto.getId());
        assertEquals(produktEntity.getName(), produktDto.getName());
        assertEquals(produktEntity.getCountry(), produktDto.getCountry());
        assertEquals(produktEntity.getPrice(), produktDto.getPrice());
    }

    @Test
    public void toEntityTest() {
        ProduktDto produktDto = ProduktDto.builder()
                .id(1)
                .name("Coke")
                .country("Ukraine")
                .price(15.99)
                .build();

        ProduktEntity produktEntity = ProductConverter.toEntity(produktDto);

        assertEquals(produktDto.getId(), produktEntity.getId());
        assertEquals(produktDto.getName(), produktEntity.getName());
        assertEquals(produktDto.getCountry(), produktEntity.getCountry());
        assertEquals(produktDto.getPrice(), produktEntity.getPrice());
    }
}
