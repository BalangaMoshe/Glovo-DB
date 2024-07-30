package com.example.demo3glovo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    private double price;
    private int quantity;

    @ManyToOne(cascade = CascadeType.DETACH)
    private OrderEntity order;

    @OneToOne(cascade = CascadeType.DETACH)
    private ProduktEntity produkt;
}
