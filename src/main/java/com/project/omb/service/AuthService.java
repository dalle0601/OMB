package com.project.omb.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthService {
    public String encryptPassword(String password) {
        // 비밀번호 암호화 로직 (예: BCrypt 사용)
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String rawPassword, String encryptedPassword) {
        // 입력 비밀번호와 저장된 암호화된 비밀번호 비교
        return BCrypt.checkpw(rawPassword, encryptedPassword);
    }
}
