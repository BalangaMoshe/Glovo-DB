package com.example.demo3glovo.servise;

import com.example.demo3glovo.converter.ItemConverter;
import com.example.demo3glovo.dto.ItemDto;
import com.example.demo3glovo.entity.ItemEntity;
import com.example.demo3glovo.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @Test
    public void saveTest_ok() {
        int id = 1;
        ItemDto itemDto = ItemDto.builder().id(id).build();
        ItemEntity itemEntity = ItemConverter.toEntity(itemDto);

        when(itemRepository.save(any(ItemEntity.class))).thenReturn(itemEntity);

        ItemDto current = itemService.update(itemDto);

        assertEquals(itemDto.getId(), current.getId());
        verify(itemRepository, times(1)).save(any(ItemEntity.class));
    }

    @Test
    public void findByOrderIdTest_ok() {
        int orderId = 1;
        List<ItemDto> itemDtos = List.of(
                ItemDto.builder().id(1).build(),
                ItemDto.builder().id(2).build()
        );
        List<ItemEntity> itemEntities = itemDtos.stream()
                .map(ItemConverter::toEntity)
                .toList();

        when(itemRepository.getByOrderId(orderId)).thenReturn(itemEntities);

        List<ItemDto> current = itemService.findByOrderId(orderId);

        assertEquals(itemDtos.size(), current.size());
        for (int i = 0; i < itemDtos.size(); i++) {
            assertEquals(itemDtos.get(i).getId(), current.get(i).getId());
        }
        verify(itemRepository, times(1)).getByOrderId(orderId);
    }

    @Test
    public void updateTest_ok() {
        int id = 1;
        ItemDto itemDto = ItemDto.builder().id(id).build();
        ItemEntity itemEntity = ItemConverter.toEntity(itemDto);

        when(itemRepository.save(any(ItemEntity.class))).thenReturn(itemEntity);

        ItemDto current = itemService.update(itemDto);

        assertEquals(itemDto.getId(), current.getId());
        verify(itemRepository, times(1)).save(any(ItemEntity.class));
    }

    @Test
    public void deleteByIdTest_ok() {
        int id = 1;

        doNothing().when(itemRepository).deleteById(id);
        itemService.delete(id);

        verify(itemRepository, times(1)).deleteById(id);
    }
}
