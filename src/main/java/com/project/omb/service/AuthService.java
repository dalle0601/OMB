package com.project.omb.service;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthService {
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encryptPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
}
