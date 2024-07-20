package com.example.demo3glovo.converter;

import com.example.demo3glovo.dto.OrderDto;
import com.example.demo3glovo.entity.ItemEntity;
import com.example.demo3glovo.entity.OrderEntity;

public class OrderConverter {
    public static OrderDto toDto(OrderEntity orderEntity) {
        return OrderDto.builder()
                .id(orderEntity.getId())
                .checkoutDate(orderEntity.getCheckoutDate())
                .userName(orderEntity.getUserName())
                .totalPrice(orderEntity.getTotalPrice())
                .items(orderEntity.getItems().stream().map(ItemEntity::getId).toList())
                .build();
    }


    public static OrderEntity toEntity(OrderDto orderDto) {
        return OrderEntity.builder()
                .id(orderDto.getId())
                .totalPrice(orderDto.getTotalPrice())
                .userName(orderDto.getUserName())
                .checkoutDate(orderDto.getCheckoutDate())
                .build();
    }
}
