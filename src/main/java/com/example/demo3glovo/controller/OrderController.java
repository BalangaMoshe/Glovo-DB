package com.example.demo3glovo.controller;

import com.example.demo3glovo.dto.ItemDto;
import com.example.demo3glovo.dto.OrderDto;
import com.example.demo3glovo.servise.ItemService;
import com.example.demo3glovo.servise.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ItemService itemService;

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable int id) {
        return orderService.findById(id);
    }

    @PostMapping
    public OrderDto save(@RequestBody OrderDto orderDto) {
        return orderService.save(orderDto);
    }

    @PostMapping("/{orderId}/items")
    public OrderDto addItem(@PathVariable int orderId, @RequestBody ItemDto itemDto) {
        return orderService.addItem(orderId, itemDto);
    }

    @GetMapping("/{orderId}/items")
    public List<ItemDto> getItems(@PathVariable int orderId) {
        return itemService.findByOrderId(orderId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        orderService.delete(id);
    }
}
