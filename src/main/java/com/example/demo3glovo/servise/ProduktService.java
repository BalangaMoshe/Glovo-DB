package com.example.demo3glovo.servise;

import com.example.demo3glovo.converter.ProductConverter;
import com.example.demo3glovo.dto.ProduktDto;
import com.example.demo3glovo.entity.ProduktEntity;
import com.example.demo3glovo.repository.ProduktRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProduktService {
    private ProduktRepository produktRepository;


    public ProduktDto getById(int id) {
        ProduktEntity produktEntity = produktRepository.findById(id).orElseThrow();
        return ProductConverter.toDto(produktEntity);
    }

    public List<ProduktDto> getAll() {
        return produktRepository.findAll().stream()
                .map(ProductConverter::toDto)
                .toList();
    }

    public ProduktDto save(ProduktDto produktDto) {
        var produkt = produktRepository.save(ProductConverter.toEntity(produktDto));
        return ProductConverter.toDto(produkt);
    }
    public ProduktDto update(ProduktDto produktDto) {
        return save(produktDto);
    }

    public void delete(int id) {
        produktRepository.deleteById(id);
    }

}
