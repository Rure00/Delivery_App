package com.delivery.app.Delivery.data.entity;

import com.delivery.app.Delivery.data.State;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
