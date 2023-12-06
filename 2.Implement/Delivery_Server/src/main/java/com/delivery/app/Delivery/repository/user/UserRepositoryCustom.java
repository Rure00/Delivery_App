package com.delivery.app.Delivery.repository.user;

import com.delivery.app.Delivery.data.entity.User;

public interface UserRepositoryCustom {
    User getUser(String id, String pwd);

    Boolean isDuplicated(String id);
}
