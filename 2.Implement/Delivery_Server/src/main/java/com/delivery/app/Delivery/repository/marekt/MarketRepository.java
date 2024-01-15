package com.delivery.app.Delivery.repository.marekt;

import com.delivery.app.Delivery.data.entity.Market;
import com.delivery.app.Delivery.repository.user.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("marketRepositorySupport")
public interface MarketRepository extends JpaRepository<Market, Long>, MarketRepositoryCustom  {
    Optional<Market> findByLoginId(String loginId);
}
