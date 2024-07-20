package com.example.demo3glovo.repository;

import com.example.demo3glovo.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
    List<ItemEntity> getByOrderId(int orderId);
}
