package com.example.devcourse_aws1.domain.user.controller;

import com.example.devcourse_aws1.domain.user.dto.response.UserResponse;
import com.example.devcourse_aws1.domain.user.repository.UserRepository;
import com.example.devcourse_aws1.domain.user.service.UserService;
import com.example.devcourse_aws1.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    // 사용자 조회
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable Long userId) {
        UserResponse user = userService.getUserById(userId);
        return ApiResponse.success(user);
    }
}
