package com.example.demo3glovo.servise;

import com.example.demo3glovo.converter.ItemConverter;
import com.example.demo3glovo.dto.ItemDto;
import com.example.demo3glovo.entity.ItemEntity;
import com.example.demo3glovo.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemDto save(ItemDto itemDto) {
        ItemEntity itemEntity = itemRepository.save(ItemConverter.toEntity(itemDto));
        return ItemConverter.toDto(itemEntity);
    }

    public List<ItemDto> findByOrderId(int orderId) {
        return itemRepository.getByOrderId(orderId).stream()
                .map(ItemConverter::toDto)
                .toList();
    }

    public ItemDto update(ItemDto itemDto) {
        ItemEntity itemEntity = itemRepository.save(ItemConverter.toEntity(itemDto));
        return ItemConverter.toDto(itemEntity);
    }

    public void delete(int id) {
        itemRepository.deleteById(id);
    }

}
