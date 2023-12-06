package com.delivery.app.Delivery.data.repository.User;

import com.delivery.app.Delivery.data.dto.SignUpDto;
import com.delivery.app.Delivery.data.entity.User;
import org.springframework.data.repository.query.Param;

public interface UserRepositoryCustom {
    User getUser(String id, String pwd);

    Boolean isDuplicated(String id);
}
