package com.project.omb.facade;

import com.project.omb.domain.User;
import com.project.omb.dto.LoginReqDTO;
import com.project.omb.dto.UserReqDTO;
import com.project.omb.dto.UserResDTO;
import com.project.omb.service.AuthService;
import com.project.omb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public UserFacade(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    public UserResDTO registerUser(UserReqDTO userReqDTO) {
        // 비밀번호 암호화
        String encryptedPassword = authService.encryptPassword(userReqDTO.getPassword());

        // 암호화된 비밀번호로 User 객체 생성
        User user = new User(
                userReqDTO.getUserName(),
                encryptedPassword,
                userReqDTO.getEmail(),
                userReqDTO.getGender()
        );

        // 사용자 정보 저장
        userService.saveUser(user);

        // 응답 DTO 구성
        return mapToUserResDTO(user);
    }

    public UserResDTO loginUser(LoginReqDTO loginReqDTO) {
        // 사용자 조회 및 검증
        User user = userService.findByUserName(loginReqDTO.getUserName())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!authService.checkPassword(loginReqDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공 시 응답 DTO 구성
        return mapToUserResDTO(user);
    }

    // 공통 로직: User -> UserResDTO 변환
    private UserResDTO mapToUserResDTO(User user) {
        return UserResDTO.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .build();
    }
}