package com.delivery.app.Delivery.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity @Table(name="stocks")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime releasedDate;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private int weight;
}
