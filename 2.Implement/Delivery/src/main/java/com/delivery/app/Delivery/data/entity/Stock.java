package com.delivery.app.Delivery.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name="stocks")
public class Stock {
    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime releasedDate;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private int weight;
}
