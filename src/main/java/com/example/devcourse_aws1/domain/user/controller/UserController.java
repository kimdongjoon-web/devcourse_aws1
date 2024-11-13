package com.example.devcourse_aws1.domain.user.controller;

import com.example.devcourse_aws1.domain.user.dto.request.UserRequest;
import com.example.devcourse_aws1.domain.user.dto.response.UserResponse;
import com.example.devcourse_aws1.domain.user.service.UserService;
import com.example.devcourse_aws1.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Create
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(userService.create(request)));
    }

    // Read
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.success(userService.getById(userId)));
    }

    // Read(List)
    @GetMapping
    public ResponseEntity<ApiResponse<Page<UserResponse>>> getList(
            @PageableDefault(size = 10, sort = "userId", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(userService.getList(pageable)));
    }

    // Update
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> update(
            @PathVariable Long userId,
            @Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(ApiResponse.success(userService.update(userId, request)));
    }

    // Delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
