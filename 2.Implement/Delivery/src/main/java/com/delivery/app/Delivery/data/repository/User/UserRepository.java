package com.delivery.app.Delivery.data.repository.User;

import com.delivery.app.Delivery.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepositorySupport")
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

}
