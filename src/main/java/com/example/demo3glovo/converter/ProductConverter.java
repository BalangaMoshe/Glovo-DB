package com.example.demo3glovo.converter;

import com.example.demo3glovo.dto.ProduktDto;
import com.example.demo3glovo.entity.ProduktEntity;

public class ProductConverter {
    public static ProduktDto toDto(ProduktEntity produktEntity) {
        return ProduktDto.builder()
                .id(produktEntity.getId())
                .name(produktEntity.getName())
                .country(produktEntity.getCountry())
                .price(produktEntity.getPrice())
                .build();
    }

    public static ProduktEntity toEntity(ProduktDto produktDto) {
        return ProduktEntity.builder()
                .id(produktDto.getId())
                .name(produktDto.getName())
                .country(produktDto.getCountry())
                .price(produktDto.getPrice())
                .build();
    }

}
