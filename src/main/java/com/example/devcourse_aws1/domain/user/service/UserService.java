package com.example.devcourse_aws1.domain.user.service;

import com.example.devcourse_aws1.domain.user.dto.response.UserResponse;
import com.example.devcourse_aws1.domain.user.repository.UserRepository;
import com.example.devcourse_aws1.global.exception.BaseException;
import com.example.devcourse_aws1.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 사용자 조회
    public UserResponse getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(UserResponse::from)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
    }
}
