package com.example.devcourse_aws1.domain.user.service;

import com.example.devcourse_aws1.domain.user.dto.request.UserRequest;
import com.example.devcourse_aws1.domain.user.dto.response.UserResponse;
import com.example.devcourse_aws1.domain.user.entity.User;
import com.example.devcourse_aws1.domain.user.repository.UserRepository;
import com.example.devcourse_aws1.global.exception.BaseException;
import com.example.devcourse_aws1.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Create
    @Transactional
    public UserResponse create(UserRequest request) {
        validateDuplicateUser(request);
        User user = User.builder()
                .loginId(request.getLoginId())
                .password(request.getPassword())  // 암호화 필요
                .name(request.getName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .build();
        return new UserResponse(userRepository.save(user));
    }

    // Read
    public UserResponse getById(Long userId) {
        return new UserResponse(findUser(userId));
    }

    // Read(List)
    public Page<UserResponse> getList(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserResponse::new);
    }

    // Update
    @Transactional
    public UserResponse update(Long userId, UserRequest request) {
        User user = findUser(userId);
        validateDuplicateUserForUpdate(request, user);
        user.update(request.getName(), request.getPhone(), request.getEmail());
        return new UserResponse(user);
    }

    // Delete
    @Transactional
    public void delete(Long userId) {
        userRepository.delete(findUser(userId));
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
    }

    private void validateDuplicateUser(UserRequest request) {
        if (userRepository.existsByLoginId(request.getLoginId())) {
            throw new BaseException(ErrorCode.LOGIN_ID_ALREADY_EXISTS);
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BaseException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        if (userRepository.existsByPhone(request.getPhone())) {
            throw new BaseException(ErrorCode.PHONE_ALREADY_EXISTS);
        }
    }

    private void validateDuplicateUserForUpdate(UserRequest request, User user) {
        if (!user.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {
            throw new BaseException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        if (!user.getPhone().equals(request.getPhone())
                && userRepository.existsByPhone(request.getPhone())) {
            throw new BaseException(ErrorCode.PHONE_ALREADY_EXISTS);
        }
    }
}
