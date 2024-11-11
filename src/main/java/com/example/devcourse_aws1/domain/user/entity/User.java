package com.example.devcourse_aws1.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 20, unique = true, nullable = false)
    private String loginId;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    /**
     * 객체의 초기 생성 담당
     * 모든 필수 필드를 포함하여 유효한 상태로 생성됨을 보장
     * 빌더 패턴으로 가독성 있는 객체 생성 지원
     */
    @Builder
    public User(String loginId, String password, String name, String phone, String email) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // 이미 생성된 객체의 수정 가능한 데이터만 변경
    public void update(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
