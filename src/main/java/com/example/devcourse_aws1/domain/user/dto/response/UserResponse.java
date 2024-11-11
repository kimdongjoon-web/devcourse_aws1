package com.example.devcourse_aws1.domain.user.dto.response;

import com.example.devcourse_aws1.domain.user.entity.Role;
import com.example.devcourse_aws1.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserResponse {

    private final Long id;
    private final String loginId;
    private final String name;
    private final String phone;
    private final String email;
    private final Role role;

    private UserResponse(User user) {
        this.id = user.getUserId();
        this.loginId = user.getLoginId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    // 정적 팩토리 메서드
    public static UserResponse from(User user) {
        return new UserResponse(user);
    }
}
