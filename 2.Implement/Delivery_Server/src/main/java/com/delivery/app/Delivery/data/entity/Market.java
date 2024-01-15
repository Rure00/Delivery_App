package com.delivery.app.Delivery.data.entity;

import com.delivery.app.Delivery.data.dto.response.market.MarketResponseDto;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity @Table(name="markets")
@RequiredArgsConstructor
@Getter
public class Market {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name="login_Id",nullable = false)
    private String loginId;

    @Column(name="login_pwd", nullable = false)
    private String loginPwd;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Stock> stocks;

    @Column()
    private String description;

    @CreatedDate
    @Column(name="create_at")
    private LocalDateTime createAt;

    public Market(String name, String loginId, String loginPwd, String phoneNumber, String address, Double latitude, Double longitude, String description) {
        this.name = name;
        this.loginId = loginId;
        this.loginPwd = loginPwd;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public MarketResponseDto toMarketResponseDto() {
        return new MarketResponseDto(
                getId(),
                getName(),
                getPhoneNumber(),
                getAddress(),
                getLatitude(),
                getLongitude(),
                getDescription()
        );
    }



}
