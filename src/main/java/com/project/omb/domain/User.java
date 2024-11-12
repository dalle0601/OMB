package com.project.omb.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// User 엔티티
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor // 모든 필드를 사용하는 생성자를 추가
@Builder
@Table(name = "OMB_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "GENDER")
    private String gender;

    // 필요한 경우, 이 생성자는 빌더 사용 또는 수동 생성시 사용됨
    public User(String userName, String password, String email, String gender) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.gender = gender;
    }
}
