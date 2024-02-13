package com.delivery.app.Delivery.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity @Table(name="reviews")
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private User user;
    @Column(name="user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name="market_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Market market;
    @Column(name="market_id")
    private Long marketId;

    @Column(name="user_nickname", nullable = false)
    private String userNickname;

    @Column(name="market_name", nullable = false)
    private String marketName;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Float score;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Review(Long userId, Long marketId, String marketName, String userNickname,String comment, Float score) {
        this.id = 0L;
        this.userId = userId;
        this.marketId = marketId;
        this.marketName = marketName;
        this.userNickname = userNickname;
        this.comment = comment;
        this.score = score;
        //this.createdAt = LocalDateTime.now();
    }
}
