package com.delivery.app.Delivery.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;

@Entity @Table(name="reviews")
@Getter
@AllArgsConstructor
public class Review {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
    private User userId;

    @OneToOne
    @JoinColumn(name="market_id", referencedColumnName = "id", nullable = false)
    private Market marketId;

    @Column(name="user_nickname", nullable = false)
    private String userNickname;

    @Column(name="market_name", nullable = false)
    private String marketName;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private int score;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
