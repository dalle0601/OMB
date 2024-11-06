package com.project.omb.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Builder
@Table(name = "omb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private String email;
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
