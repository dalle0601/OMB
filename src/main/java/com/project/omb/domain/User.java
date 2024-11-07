package com.project.omb.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Builder
@Table(name = "OMB_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Long id;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "GENDER")
    private String gender;

    public User(Long id, String userName, String password, String email, String gender) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.gender = gender;
    }
    public User(String userName, String password, String email, String gender) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.gender = gender;
    }

}
