package com.example.demo3glovo.converter;

import com.example.demo3glovo.dto.OrderDto;
import com.example.demo3glovo.entity.OrderEntity;
import com.example.demo3glovo.entity.ItemEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderConverterTest {

    @Test
    public void toDtoTest() {
        OrderEntity orderEntity = OrderEntity.builder()
                .id(1)
                .userName("Vasyl")
                .checkoutDate(LocalDate.of(2024, 7, 30))
                .totalPrice(100.0)
                .items(List.of(ItemEntity.builder().id(1).build(), ItemEntity.builder().id(2).build()))
                .build();

        OrderDto orderDto = OrderConverter.toDto(orderEntity);

        assertEquals(orderEntity.getId(), orderDto.getId());
        assertEquals(orderEntity.getUserName(), orderDto.getUserName());
        assertEquals(orderEntity.getItems().stream().map(ItemEntity::getId).toList(), orderDto.getItems());
    }

    @Test
    public void toEntityTest() {
        OrderDto orderDto = OrderDto.builder()
                .id(1)
                .userName("John Doe")
                .checkoutDate(LocalDate.of(2024, 7, 30))
                .totalPrice(100.0)
                .items(List.of(1, 2))
                .build();

        OrderEntity orderEntity = OrderConverter.toEntity(orderDto);

        assertEquals(orderDto.getId(), orderEntity.getId());
        assertEquals(orderDto.getUserName(), orderEntity.getUserName());
        assertEquals(orderDto.getCheckoutDate(), orderEntity.getCheckoutDate());
        assertEquals(orderDto.getTotalPrice(), orderEntity.getTotalPrice());

        List<Integer> dtoItemIds = orderDto.getItems();
        List<Integer> entityItemIds = orderEntity.getItems().stream()
                .map(ItemEntity::getId)
                .toList();

        assertEquals(dtoItemIds.size(), entityItemIds.size());
        assertEquals(dtoItemIds, entityItemIds);
    }
}
