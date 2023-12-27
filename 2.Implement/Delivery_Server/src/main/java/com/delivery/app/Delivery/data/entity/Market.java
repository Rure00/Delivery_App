package com.delivery.app.Delivery.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.locationtech.jts.geom.Point;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity @Table(name="markets")
@AllArgsConstructor
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
    private Point location;

    @OneToMany
    private Collection<Stock> stocks;

    @Column()
    private String description;

    @CreatedDate
    @Column(name="create_at")
    private LocalDateTime createAt;

}
