package com.delivery.app.Delivery.repository.user;

import com.delivery.app.Delivery.data.entity.QUser;
import com.delivery.app.Delivery.data.entity.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

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
