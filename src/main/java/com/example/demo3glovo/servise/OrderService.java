package com.example.demo3glovo.servise;

import com.example.demo3glovo.converter.ItemConverter;
import com.example.demo3glovo.converter.OrderConverter;
import com.example.demo3glovo.dto.ItemDto;
import com.example.demo3glovo.dto.OrderDto;
import com.example.demo3glovo.entity.ItemEntity;
import com.example.demo3glovo.entity.OrderEntity;
import com.example.demo3glovo.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final com.example.demo3glovo.servise.ItemService itemService;

    public OrderDto findById(int id) {
        return orderRepository.findById(id)
                .map(OrderConverter::toDto)
                .orElseThrow();
    }

    public OrderDto save(OrderDto orderDto) {
        OrderEntity orderEntity = orderRepository.save(OrderConverter.toEntity(orderDto));
        return OrderConverter.toDto(orderEntity);
    }

    public OrderDto addItem(int orderId, ItemDto itemDto) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow();
        ItemDto savedItem = itemService.save(itemDto);
        ItemEntity itemEntity = ItemConverter.toEntity(savedItem);
        orderEntity.getItems().add(itemEntity);
        orderEntity = orderRepository.save(orderEntity);
        return OrderConverter.toDto(orderEntity);
    }

    public void delete(int id) {
        orderRepository.deleteById(id);
    }

}
