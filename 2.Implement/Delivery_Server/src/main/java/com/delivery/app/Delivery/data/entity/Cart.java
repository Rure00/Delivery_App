package com.delivery.app.Delivery.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Table(name="carts")
@Getter
@NoArgsConstructor
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
    @Column(name="user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="market_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Market market;
    @Column(name="market_id")
    private Long marketId;

    @Column(name="market_name")
    private String marketName;

    @Column()
    private Integer cost;


    public Cart(Long userId, Long marketId, String marketName, Integer cost) {
        //ID만 이용해서 매핑하기) https://gksdudrb922.tistory.com/231
        this.userId = userId;
        this.marketId = marketId;
        this.marketName = marketName;
        this.cost = cost;
    }

}
