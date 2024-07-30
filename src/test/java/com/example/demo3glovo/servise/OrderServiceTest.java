package com.example.demo3glovo.servise;

import com.example.demo3glovo.converter.ItemConverter;
import com.example.demo3glovo.converter.OrderConverter;
import com.example.demo3glovo.dto.ItemDto;
import com.example.demo3glovo.dto.OrderDto;
import com.example.demo3glovo.entity.OrderEntity;
import com.example.demo3glovo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ItemService itemService;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void findByIdTest_ok() {
        int id = 1;
        OrderDto orderDto = OrderDto.builder().id(id).items(new ArrayList<>()).build();
        OrderEntity orderEntity = OrderConverter.toEntity(orderDto);

        when(orderRepository.findById(id)).thenReturn(Optional.of(orderEntity));

        OrderDto current = orderService.findById(id);

        assertEquals(orderDto.getId(), current.getId());
        assertEquals(id, current.getId());
        verify(orderRepository, times(1)).findById(id);
    }

    @Test
    void saveTest_ok() {
        int id = 1;
        OrderDto orderDto = OrderDto.builder().id(id).items(new ArrayList<>()).build();
        OrderEntity orderEntity = OrderConverter.toEntity(orderDto);

        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        OrderDto current = orderService.save(orderDto);

        assertEquals(orderDto, current);
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
    }

    @Test
    public void addItemTest_ok() {
        int orderId = 1;
        int itemId = 12;

        ItemDto itemDto = ItemDto.builder().id(itemId).build();
        ItemConverter.toEntity(itemDto);

        OrderEntity orderEntity = OrderEntity.builder().id(orderId).items(new ArrayList<>()).build();
        OrderDto orderDto = OrderDto.builder().id(orderId).items(List.of(itemId)).build();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(orderEntity));
        when(itemService.save(any(ItemDto.class))).thenReturn(itemDto);
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        OrderDto actual = orderService.addItem(orderId, itemDto);

        assertEquals(orderDto.getItems(), actual.getItems());
        verify(orderRepository, times(1)).findById(orderId);
        verify(itemService, times(1)).save(any(ItemDto.class));
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
    }

    @Test
    public void deleteTest_ok() {
        int id = 1;

        doNothing().when(orderRepository).deleteById(id);
        orderService.delete(id);

        verify(orderRepository, times(1)).deleteById(id);
    }
}
