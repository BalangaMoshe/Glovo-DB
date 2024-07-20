package com.example.demo3glovo.controller;

import com.example.demo3glovo.dto.ProduktDto;
import com.example.demo3glovo.servise.ProduktService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produkts")
@AllArgsConstructor

public class ProduktController {
    private final ProduktService produktService;

    @GetMapping
    public List<ProduktDto> getAll() {
        return produktService.getAll();
    }

    @GetMapping("/{id}")
    public ProduktDto findById(@PathVariable int id) {
        return produktService.getById(id);
    }

    @PostMapping
    public ProduktDto create(@RequestBody ProduktDto produktDto) {
        return produktService.save(produktDto);
    }

    @PutMapping
    public ProduktDto update(@RequestBody ProduktDto produktDto) {
        return produktService.update(produktDto);
    }

    @DeleteMapping("/{id}")
    public void delate(@PathVariable int id) {
        produktService.delete(id);
    }

}
