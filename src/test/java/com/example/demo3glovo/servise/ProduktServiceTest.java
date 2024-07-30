package com.example.demo3glovo.servise;

import com.example.demo3glovo.dto.ProduktDto;
import com.example.demo3glovo.entity.ProduktEntity;
import com.example.demo3glovo.repository.ProduktRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProduktServiceTest {

    @Mock
    private ProduktRepository produktRepository;

    @InjectMocks
    private ProduktService produktService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetById() {
        ProduktEntity produktEntity = new ProduktEntity();
        produktEntity.setId(1);

        when(produktRepository.findById(1)).thenReturn(Optional.of(produktEntity));

        ProduktDto produktDto = produktService.getById(1);

        assertEquals(1, produktDto.getId());
        verify(produktRepository, times(1)).findById(1);
    }

    @Test
    public void testGetAll() {
        ProduktEntity produktEntity = new ProduktEntity();
        produktEntity.setId(1);

        when(produktRepository.findAll()).thenReturn(List.of(produktEntity));

        List<ProduktDto> produkts = produktService.getAll();

        assertEquals(1, produkts.size());
        verify(produktRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        ProduktEntity produktEntity = new ProduktEntity();
        produktEntity.setId(1);

        when(produktRepository.save(any(ProduktEntity.class))).thenReturn(produktEntity);

        ProduktDto produktDto = new ProduktDto();
        produktDto.setId(1);

        ProduktDto savedProduktDto = produktService.save(produktDto);

        assertEquals(1, savedProduktDto.getId());
        verify(produktRepository, times(1)).save(any(ProduktEntity.class));
    }

    @Test
    public void testDelete() {
        produktService.delete(1);
        verify(produktRepository, times(1)).deleteById(1);
    }
}
