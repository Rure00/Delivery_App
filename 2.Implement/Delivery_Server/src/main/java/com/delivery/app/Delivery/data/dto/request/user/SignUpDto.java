package com.delivery.app.Delivery.data.dto.request.user;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private String name;
    private String nickname;
    private String loginId;
    private String loginPwd;
    private String phoneNumber;
    private String gender;
    private String address;

    public boolean hasNull() {
        return     name == null
                || nickname == null
                || loginId == null
                || loginPwd == null
                || phoneNumber == null
                || gender == null
                || address == null;
    }
}
