package com.example.demo3glovo.controller;

import com.example.demo3glovo.dto.ItemDto;
import com.example.demo3glovo.servise.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@AllArgsConstructor

public class ItemController {
    private final ItemService itemService;

    @PutMapping
    public ItemDto update(@RequestBody ItemDto itemDto) {
        return itemService.update(itemDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        itemService.delete(id);
    }
}

