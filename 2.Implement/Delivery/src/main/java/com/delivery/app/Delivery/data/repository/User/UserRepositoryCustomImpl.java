package com.delivery.app.Delivery.data.repository.User;

import com.delivery.app.Delivery.data.dto.SignUpDto;
import com.delivery.app.Delivery.data.entity.QUser;
import com.delivery.app.Delivery.data.entity.User;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;

public class UserRepositoryCustomImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

    public UserRepositoryCustomImpl() { super(User.class); }

    @Override
    public User getUser(String id, String pwd) {
        QUser user = QUser.user;

        User userData = from(user)
                .where(user.loginID.eq(id).and(user.loginPwd.eq(pwd)))
                .fetchOne();

        if(userData == null)
            System.out.println("err: user is null");

        return userData;
    }

    @Override
    public Boolean isDuplicated(String id) {
        QUser user = QUser.user;
        User userData = from(user)
                .where(user.loginID.eq(id))
                .fetchOne();

        return userData!=null;
    }

}
