package com.delivery.app.Delivery.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity @Table(name="stocks")
@Getter
@AllArgsConstructor
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
